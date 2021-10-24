package uk.co.mruoc.camunda.client.deploy.get;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class GetDeploymentsResponseTest {

    @Test
    void shouldReturnNumberOfDeployments() {
        GetDeploymentsResponse response = GetDeploymentsResponseMother.build(
                DeploymentMother.build(),
                DeploymentMother.build()
        );

        int numberOfDeployments = response.getNumberOfDeployments();

        assertThat(numberOfDeployments).isEqualTo(2);
    }

    @Test
    void shouldReturnIdsFromDeployments() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        GetDeploymentsResponse response = GetDeploymentsResponseMother.build(
                DeploymentMother.withId(id1),
                DeploymentMother.withId(id2)
        );

        Collection<UUID> ids = response.getDeploymentIds();

        assertThat(ids).containsExactly(id1, id2);
    }

}
