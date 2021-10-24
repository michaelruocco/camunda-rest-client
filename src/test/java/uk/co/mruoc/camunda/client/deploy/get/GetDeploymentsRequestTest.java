package uk.co.mruoc.camunda.client.deploy.get;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetDeploymentsRequestTest {

    @Test
    void shouldReturnEmptyQueryStringIfNoValuesSet() {
        GetDeploymentsRequest request = GetDeploymentsRequestMother.empty();

        String queryString = request.toQueryString();

        assertThat(queryString).isEmpty();
    }

    @Test
    void shouldConvertValuesToQueryString() {
        GetDeploymentsRequest request = GetDeploymentsRequestMother.build();

        String queryString = request.toQueryString();

        assertThat(queryString).isEqualTo("?" +
                "before=2050-12-31T12%3A00%3A00.000%2B0000" +
                "&after=2000-01-01T12%3A00%3A00.000%2B0000");
    }

}
