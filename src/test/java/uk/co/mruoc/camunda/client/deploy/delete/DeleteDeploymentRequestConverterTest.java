package uk.co.mruoc.camunda.client.deploy.delete;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;

class DeleteDeploymentRequestConverterTest {

    private static final String DEFAULT_BASE_URI = "http://localhost:7999";

    private final HeaderPopulator headerPopulator = mock(HeaderPopulator.class);

    private final DeleteDeploymentRequestConverter converter =
            new DeleteDeploymentRequestConverter(DEFAULT_BASE_URI, headerPopulator);

    @Test
    void shouldReturnEmptyIfRequestIsNotDeleteDeploymentRequest() {
        Object request = new Object();

        Optional<HttpRequest> httpRequest = converter.toHttpRequest(request);

        assertThat(httpRequest).isEmpty();
    }

    @Test
    void shouldReturnHttpRequestUsingDefaultBaseUriIfNoOverrideUriSpecifiedInRequest() {
        DeleteDeploymentRequest request =
                DeleteDeploymentsRequestMother.builder().overrideBaseUri(null).build();

        Optional<HttpRequest> httpRequest = converter.toHttpRequest(request);

        String uri = httpRequest.map(HttpRequest::uri).map(URI::toString).orElse("");
        assertThat(uri).startsWith(DEFAULT_BASE_URI);
    }

    @Test
    void shouldReturnHttpRequestUsingOverrideUriIfSpecifiedInRequest() {
        String overrideBaseUri = "http://override:8081";
        DeleteDeploymentRequest request = DeleteDeploymentsRequestMother.builder()
                .overrideBaseUri(overrideBaseUri)
                .build();

        Optional<HttpRequest> httpRequest = converter.toHttpRequest(request);

        String uri = httpRequest.map(HttpRequest::uri).map(URI::toString).orElse("");
        assertThat(uri).startsWith(overrideBaseUri);
    }
}
