package uk.co.mruoc.camunda.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestMother.buildExternalScriptDeploymentRequest;
import static uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestMother.buildInlineScriptDeploymentRequest;
import static uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestMother.buildMessageDemoScriptDeploymentRequest;
import static uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestMother.buildMessageWithUserTaskDemoScriptDeploymentRequest;
import static uk.co.mruoc.camunda.client.message.DeliverMessageRequestMother.buildDelierMessageRequest;

import java.util.Collections;
import java.util.UUID;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequest;
import uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentResponse;
import uk.co.mruoc.camunda.client.deploy.delete.DeleteDeploymentRequest;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequest;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequestMother;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsResponse;
import uk.co.mruoc.camunda.client.message.DeliverMessageRequest;
import uk.co.mruoc.camunda.client.process.StartProcessRequest;
import uk.co.mruoc.camunda.client.process.StartProcessRequestMother;
import uk.co.mruoc.camunda.client.process.StartProcessResponse;
import uk.co.mruoc.camunda.client.processinstance.GetProcessInstancesByBusinessKeyRequest;
import uk.co.mruoc.camunda.client.processinstance.ProcessInstance;
import uk.co.mruoc.camunda.client.processinstance.ProcessInstancesResponse;
import uk.co.mruoc.camunda.client.task.GetTaskByProcessInstanceBusinessKeyRequest;
import uk.co.mruoc.camunda.client.task.Task;
import uk.co.mruoc.camunda.client.task.TasksResponse;
import uk.co.mruoc.camunda.client.variable.JsonVariable;
import uk.co.mruoc.camunda.client.variable.Variables;

@Testcontainers
class CamundaClientIntegrationTest {

    @Container
    public static final LocalCamunda CAMUNDA = new LocalCamunda();

    private static CamundaClient client;

    @BeforeAll
    static void setUp() {
        client = new CamundaClient(CAMUNDA.getUri());
    }

    @Test
    void shouldCreateDeployment() {
        CreateDeploymentRequest request = buildInlineScriptDeploymentRequest();

        CreateDeploymentResponse response = client.createDeployment(request);

        assertThat(response.getDeployedProcessDefinitions()).hasSize(1);
    }

    @Test
    void shouldStartProcess() {
        String processDefinitionKey = givenBpmnIsDeployed().getFirstDeployedProcessDefinitionKey();
        StartProcessRequest request = StartProcessRequestMother.withDefinitionProcessKey(processDefinitionKey);

        StartProcessResponse response = client.startProcess(request);

        assertThat(response.getDefinitionId()).isNotNull();
    }

    @Test
    void shouldGetDeployments() {
        givenBpmnIsDeployed();
        GetDeploymentsRequest request = GetDeploymentsRequestMother.empty();

        GetDeploymentsResponse response = client.getDeployments(request);

        assertThat(response.getDeployments()).hasSize(3);
    }

    @Test
    void shouldDeleteDeployments() {
        UUID id = givenBpmnIsDeployed().getId();
        DeleteDeploymentRequest request = new DeleteDeploymentRequest(id);

        ThrowingCallable call = () -> client.deleteDeployment(request);

        assertThatCode(call).doesNotThrowAnyException();
    }

    @Test
    void shouldSendMessageToRunningProcess() {
        CreateDeploymentResponse createResponse = givenBpmnIsDeployed(buildMessageDemoScriptDeploymentRequest());
        StartProcessRequest startRequest = StartProcessRequest.builder()
                .processDefinitionKey(createResponse.getFirstDeployedProcessDefinitionKey())
                .businessKey("abc-123")
                .build();
        StartProcessResponse startResponse = client.startProcess(startRequest);
        DeliverMessageRequest messageRequest = DeliverMessageRequest.builder()
                .messageName("receive-message")
                .businessKey(startResponse.getBusinessKey())
                .processVariables(new Variables(new JsonVariable("messageInput", "{\"value\":\"hello\"}")))
                .build();

        ThrowingCallable call = () -> client.deliverMessage(messageRequest);

        assertThatCode(call).doesNotThrowAnyException();
    }

    @Test
    void shouldGetUserTaskForRunningProcess() {
        givenBpmnIsDeployed(buildMessageWithUserTaskDemoScriptDeploymentRequest());
        String businessKey = "xyz-789";
        DeliverMessageRequest messageRequest = DeliverMessageRequest.builder()
                .messageName("receive-message-for-user-task")
                .businessKey(businessKey)
                .processVariables(new Variables(new JsonVariable("messageInput", "{\"value\":\"hello\"}")))
                .build();
        client.deliverMessage(messageRequest);
        GetTaskByProcessInstanceBusinessKeyRequest request =
                new GetTaskByProcessInstanceBusinessKeyRequest(businessKey);

        TasksResponse response = client.getTask(request);

        assertThat(response.getFirstTask()).map(Task::getId).isNotNull();
    }

    @Test
    void shouldGetProcessInstanceForTask() {
        givenBpmnIsDeployed(buildMessageWithUserTaskDemoScriptDeploymentRequest());
        String businessKey = "abc-def-123";
        givenTaskIsCreated(businessKey);

        GetProcessInstancesByBusinessKeyRequest request = GetProcessInstancesByBusinessKeyRequest.builder()
                .businessKey(businessKey)
                .processDefinitionKeyNotIn(Collections.emptyList())
                .build();

        ProcessInstancesResponse response = client.getProcessInstances(request);

        assertThat(response.getNumberOfProcessInstances()).isEqualTo(1);
        assertThat(response.getFirstProcessInstance().map(ProcessInstance::getId))
                .isNotNull();
    }

    private static CreateDeploymentResponse givenBpmnIsDeployed() {
        return givenBpmnIsDeployed(buildExternalScriptDeploymentRequest());
    }

    private static CreateDeploymentResponse givenBpmnIsDeployed(CreateDeploymentRequest request) {
        return client.createDeployment(request);
    }

    private static void givenTaskIsCreated(String businessKey) {
        client.deliverMessage(buildDelierMessageRequest(businessKey));
    }
}
