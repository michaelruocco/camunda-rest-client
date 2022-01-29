package uk.co.mruoc.camunda.client.header;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import java.net.http.HttpRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

class CompositeHeaderPopulatorTest {

    private final HeaderPopulator populator1 = mock(HeaderPopulator.class);
    private final HeaderPopulator populator2 = mock(HeaderPopulator.class);

    private final HeaderPopulator compositePopulator = new CompositeHeaderPopulator(populator1, populator2);

    @Test
    void shouldReturnBuilder() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        HttpRequest.Builder populatedBuilder = compositePopulator.populate(builder);

        assertThat(populatedBuilder).isEqualTo(builder);
    }

    @Test
    void shouldPassBuilderToAllOtherPopulators() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        compositePopulator.populate(builder);

        InOrder inOrder = inOrder(populator1, populator2);
        inOrder.verify(populator1).populate(builder);
        inOrder.verify(populator2).populate(builder);
    }
}
