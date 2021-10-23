package uk.co.mruoc.camunda.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequest;
import uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentResponse;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsResponse;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequest;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequestMother;
import uk.co.mruoc.camunda.client.process.StartProcessRequestMother;
import uk.co.mruoc.camunda.client.process.StartProcessRequest;
import uk.co.mruoc.camunda.client.process.StartProcessResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestMother.buildExternalScriptDeploymentRequest;
import static uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestMother.buildInlineScriptDeploymentRequest;

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
        String processDefinitionKey = givenBpmnIsDeployed();
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

    private static String givenBpmnIsDeployed() {
        CreateDeploymentResponse response = client.createDeployment(buildExternalScriptDeploymentRequest());
        return response.getFirstDeployedProcessDefinitionKey();
    }

}
