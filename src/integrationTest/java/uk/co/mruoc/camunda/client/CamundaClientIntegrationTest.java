package uk.co.mruoc.camunda.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import uk.co.mruoc.camunda.client.deploy.CreateDeploymentRequest;
import uk.co.mruoc.camunda.client.deploy.CreateDeploymentResponse;
import uk.co.mruoc.camunda.client.process.StartProcessRequestMother;
import uk.co.mruoc.camunda.client.process.StartProcessRequest;
import uk.co.mruoc.camunda.client.process.StartProcessResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.camunda.client.deploy.CreateDeploymentRequestMother.buildExternalScriptDeploymentRequest;
import static uk.co.mruoc.camunda.client.deploy.CreateDeploymentRequestMother.buildInlineScriptDeploymentRequest;

@Testcontainers
public class CamundaClientIntegrationTest {

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

    private static String givenBpmnIsDeployed() {
        CreateDeploymentResponse response = client.createDeployment(buildExternalScriptDeploymentRequest());
        return response.getFirstDeployedProcessDefinitionKey();
    }

}
