package uk.co.mruoc.camunda.client;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import uk.co.mruoc.camunda.client.deployment.CreateDeploymentRequest;
import uk.co.mruoc.camunda.client.deployment.StartProcessRequestMother;
import uk.co.mruoc.camunda.client.process.StartProcessRequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static uk.co.mruoc.camunda.client.deployment.CreateDeploymentRequestMother.buildExternalScriptDeploymentRequest;
import static uk.co.mruoc.camunda.client.deployment.CreateDeploymentRequestMother.buildInlineScriptDeploymentRequest;

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

        ThrowingCallable call = () -> client.createDeployment(request);

        assertThatCode(call).doesNotThrowAnyException();
    }

    @Test
    void shouldStartProcess() {
        givenBpmnIsDeployed();
        //TODO get returned process key of deployment and pass into start process request
        StartProcessRequest request = StartProcessRequestMother.build();

        ThrowingCallable call = () -> client.startProcess(request);

        assertThatCode(call).doesNotThrowAnyException();
    }

    private static void givenBpmnIsDeployed() {
        client.createDeployment(buildExternalScriptDeploymentRequest());
        //TODO return process key of deployment
    }

}
