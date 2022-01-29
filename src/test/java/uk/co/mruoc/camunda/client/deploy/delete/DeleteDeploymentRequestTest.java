package uk.co.mruoc.camunda.client.deploy.delete;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DeleteDeploymentRequestTest {

    @Test
    void shouldReturnEmptyQueryStringIfNoValuesSet() {
        DeleteDeploymentRequest request = DeleteDeploymentsRequestMother.build();

        String queryString = request.toQueryString();

        assertThat(queryString).isEmpty();
    }

    @Test
    void shouldConvertValuesToQueryString() {
        DeleteDeploymentRequest request = DeleteDeploymentsRequestMother.withQueryStringArgs();

        String queryString = request.toQueryString();

        assertThat(queryString).isEqualTo("?" + "cascade=true" + "&skipCustomListeners=true");
    }
}
