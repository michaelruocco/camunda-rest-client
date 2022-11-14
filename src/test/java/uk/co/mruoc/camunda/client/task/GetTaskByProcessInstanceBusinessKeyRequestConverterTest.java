package uk.co.mruoc.camunda.client.task;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class GetTaskByProcessInstanceBusinessKeyRequestConverterTest {

    private static final String DEFAULT_BASE_URI = "http://localhost:7999";

    private final HeaderPopulator headerPopulator = mock(HeaderPopulator.class);

    private final GetTaskByProcessInstanceBusinessKeyRequestConverter converter =
            new GetTaskByProcessInstanceBusinessKeyRequestConverter(DEFAULT_BASE_URI, headerPopulator);

    @Test
    void shouldReturnEmptyIfRequestIsNotGetTaskByProcessInstanceBusinessKeyRequest() {
        Object request = new Object();

        Optional<HttpRequest> httpRequest = converter.toHttpRequest(request);

        assertThat(httpRequest).isEmpty();
    }

    @Test
    void shouldReturnHttpRequestWithBusinessKeyAsQueryStringParameter() {
        String businessKey = "test-business-key";
        GetTaskByProcessInstanceBusinessKeyRequest request = new GetTaskByProcessInstanceBusinessKeyRequest(businessKey);

        Optional<HttpRequest> httpRequest = converter.toHttpRequest(request);

        String uri = httpRequest.map(HttpRequest::uri).map(URI::toString).orElse("");
        assertThat(uri).isEqualTo("http://localhost:7999/engine-rest/task?processInstanceBusinessKey=test-business-key");
    }
}
