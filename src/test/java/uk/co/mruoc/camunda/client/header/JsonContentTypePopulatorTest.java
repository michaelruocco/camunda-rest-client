package uk.co.mruoc.camunda.client.header;

import org.junit.jupiter.api.Test;

import java.net.http.HttpRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class JsonContentTypePopulatorTest {

    private final HeaderPopulator populator = new JsonContentTypePopulator();

    @Test
    void shouldReturnBuilder() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        HttpRequest.Builder populatedBuilder = populator.populate(builder);

        assertThat(populatedBuilder).isEqualTo(builder);
    }

    @Test
    void shouldSetContentTypeHeaderAsApplicationJson() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        populator.populate(builder);

        verify(builder).header("Content-Type", "application/json");
    }

}
