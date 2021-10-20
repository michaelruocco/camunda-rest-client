package uk.co.mruoc.camunda.client.header;

import org.junit.jupiter.api.Test;

import java.net.http.HttpRequest;
import java.util.UUID;

import static java.net.http.HttpRequest.newBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CorrelationIdHeaderPopulatorTest {

    private static final String CORRELATION_ID_NAME = "correlation-id";
    private static final String CORRELATION_ID_VALUE = UUID.randomUUID().toString();

    private final HeaderPopulator populator = new CorrelationIdHeaderPopulator(() -> CORRELATION_ID_VALUE);

    @Test
    void shouldReturnBuilder() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        HttpRequest.Builder populatedBuilder = populator.populate(builder);

        assertThat(populatedBuilder).isEqualTo(builder);
    }

    @Test
    void shouldSetCorrelationIdHeaderWithSuppliedCorrelationIdValue() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        populator.populate(builder);

        verify(builder).header(CORRELATION_ID_NAME, CORRELATION_ID_VALUE);
    }

    @Test
    void shouldPopulateRandomCorrelationIdValueIfNoSupplierProvided() {
        HeaderPopulator randomIdPopulator = new CorrelationIdHeaderPopulator();

        String id1 = extractCorrelationId(randomIdPopulator.populate(newBuilder()));
        String id2 = extractCorrelationId(randomIdPopulator.populate(newBuilder()));

        assertThat(id1)
                .isNotEmpty()
                .isNotEqualTo(id2);
    }

    private static String extractCorrelationId(HttpRequest.Builder builder) {
        return builder.build()
                .headers()
                .firstValue(CORRELATION_ID_NAME)
                .orElseThrow(() -> new IllegalStateException(String.format("header value for %s not found", CORRELATION_ID_NAME)));
    }

}
