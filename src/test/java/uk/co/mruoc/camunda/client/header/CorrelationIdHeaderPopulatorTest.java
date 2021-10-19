package uk.co.mruoc.camunda.client.header;

import org.junit.jupiter.api.Test;

import java.net.http.HttpRequest;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CorrelationIdHeaderPopulatorTest {

    private static final String CORRELATION_ID = UUID.randomUUID().toString();

    private final HeaderPopulator populator = new CorrelationIdHeaderPopulator(() -> CORRELATION_ID);

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

        verify(builder).header("correlation-id", CORRELATION_ID);
    }

}
