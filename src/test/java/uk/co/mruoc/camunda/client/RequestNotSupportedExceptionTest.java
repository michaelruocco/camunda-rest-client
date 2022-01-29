package uk.co.mruoc.camunda.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RequestNotSupportedExceptionTest {

    @Test
    void shouldReturnMessage() {
        Object request = new Object();

        Throwable error = new RequestNotSupportedException(request);

        assertThat(error.getMessage()).isEqualTo("request of type java.lang.Object is not supported");
    }
}
