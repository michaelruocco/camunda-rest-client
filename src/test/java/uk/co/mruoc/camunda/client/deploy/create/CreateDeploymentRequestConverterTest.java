package uk.co.mruoc.camunda.client.deploy.create;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreateDeploymentRequestConverterTest {

    private static final String DEFAULT_BASE_URI = "http://localhost:7999";

    private final HeaderPopulator headerPopulator = mock(HeaderPopulator.class);

    private final CreateDeploymentRequestConverter converter = new CreateDeploymentRequestConverter(DEFAULT_BASE_URI, headerPopulator);

    @Test
    void shouldReturnEmptyIfRequestIsNotCreateDeploymentRequest() {
        Object request = new Object();

        Optional<HttpRequest> httpRequest = converter.toHttpRequest(request);

        assertThat(httpRequest).isEmpty();
    }

    @Test
    void shouldReturnHttpRequestUsingDefaultBaseUriIfNoOverrideUriSpecifiedInRequest() {
        CreateDeploymentRequest request = CreateDeploymentRequestMother.builder()
                .resources(ResourceMother.buildInlineScriptDemoResources())
                .overrideBaseUri(null)
                .build();
System.out.println(request);
        Optional<HttpRequest> httpRequest = converter.toHttpRequest(request);

        String uri = httpRequest
                .map(HttpRequest::uri)
                .map(URI::toString)
                .orElse("");
        assertThat(uri).startsWith(DEFAULT_BASE_URI);
    }

    @Test
    void shouldReturnHttpRequestUsingOverrideUriIfSpecifiedInRequest() {
        String overrideBaseUri = "http://override:8081";
        CreateDeploymentRequest request = CreateDeploymentRequestMother.builder()
                .resources(ResourceMother.buildInlineScriptDemoResources())
                .overrideBaseUri(overrideBaseUri)
                .build();

        Optional<HttpRequest> httpRequest = converter.toHttpRequest(request);

        String uri = httpRequest
                .map(HttpRequest::uri)
                .map(URI::toString)
                .orElse("");
        assertThat(uri).startsWith(overrideBaseUri);
    }

}
