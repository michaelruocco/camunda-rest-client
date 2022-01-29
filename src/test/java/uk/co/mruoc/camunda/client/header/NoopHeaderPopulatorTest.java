package uk.co.mruoc.camunda.client.header;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import java.net.http.HttpRequest;
import org.junit.jupiter.api.Test;

class NoopHeaderPopulatorTest {

    private final HeaderPopulator populator = new NoopHeaderPopulator();

    @Test
    void shouldReturnBuilder() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        HttpRequest.Builder populatedBuilder = populator.populate(builder);

        assertThat(populatedBuilder).isEqualTo(builder);
    }

    @Test
    void shouldNotInteractWithBuilder() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        populator.populate(builder);

        verifyNoInteractions(builder);
    }
}
