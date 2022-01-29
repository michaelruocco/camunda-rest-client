package uk.co.mruoc.camunda.client;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestMother.buildExternalScriptDeploymentRequest;
import static uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestMother.buildInlineScriptDeploymentRequest;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequest;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequestMother;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsResponse;

@Testcontainers
class DeploymentHousekeepingIntegrationTest {

    @Container
    public static final LocalCamunda CAMUNDA = new LocalCamunda();

    private static CamundaClient client;
    private static DeploymentHousekeeping housekeeping;

    @BeforeAll
    static void setUp() {
        client = new CamundaClient(CAMUNDA.getUri());
        housekeeping = new DeploymentHousekeeping(client);
    }

    @Test
    void shouldDeleteAllDeploymentsBeforeCutoff() {
        client.createDeployment(buildExternalScriptDeploymentRequest());
        client.createDeployment(buildInlineScriptDeploymentRequest());
        OffsetDateTime cutoff = OffsetDateTime.now();
        int initialNumberOfDeployments = getNumberOfDeploymentsBefore(cutoff);
        assertThat(initialNumberOfDeployments).isGreaterThanOrEqualTo(2);

        housekeeping.deleteDeploymentsBefore(cutoff);

        int numberOfDeployments = getNumberOfDeploymentsBefore(cutoff);
        assertThat(numberOfDeployments).isEqualTo(0);
    }

    private static int getNumberOfDeploymentsBefore(OffsetDateTime cutoff) {
        GetDeploymentsRequest request = GetDeploymentsRequestMother.before(cutoff);
        GetDeploymentsResponse response = client.getDeployments(request);
        return response.getNumberOfDeployments();
    }
}
