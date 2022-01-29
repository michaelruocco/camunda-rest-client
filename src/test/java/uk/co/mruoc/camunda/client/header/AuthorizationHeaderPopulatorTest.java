package uk.co.mruoc.camunda.client.header;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.net.http.HttpRequest;
import org.junit.jupiter.api.Test;

class AuthorizationHeaderPopulatorTest {

    private static final String TOKEN_VALUE = "token-value";

    private final HeaderPopulator populator = new AuthorizationHeaderPopulator(() -> TOKEN_VALUE);

    @Test
    void shouldReturnBuilder() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        HttpRequest.Builder populatedBuilder = populator.populate(builder);

        assertThat(populatedBuilder).isEqualTo(builder);
    }

    @Test
    void shouldSetAuthorizationHeaderWithSuppliedTokenValue() {
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);

        populator.populate(builder);

        verify(builder).header("authorization", TOKEN_VALUE);
    }
}
