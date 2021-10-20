package uk.co.mruoc.camunda.client.deploy;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;

import java.net.http.HttpRequest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreateDeploymentRequestConverterTest {

    private static final String BASE_URI = "http://localhost:7999";

    private final HeaderPopulator headerPopulator = mock(HeaderPopulator.class);

    private final CreateDeploymentRequestConverter converter = new CreateDeploymentRequestConverter(BASE_URI, headerPopulator);

    @Test
    void shouldReturnEmptyIfRequestIsNotCreateDeploymentRequest() {
        Object request = new Object();

        Optional<HttpRequest> httpRequest = converter.toHttpRequest(request);

        assertThat(httpRequest).isEmpty();
    }

}
