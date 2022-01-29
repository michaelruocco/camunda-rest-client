package uk.co.mruoc.camunda.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CamundaClientExceptionTest {

    @Test
    void shouldReturnCause() {
        Throwable cause = new Exception("boom!");

        Throwable error = new CamundaClientException(cause);

        assertThat(error.getCause()).isEqualTo(cause);
    }

    @Test
    void shouldReturnMessage() {
        String message = "error-message";

        Throwable error = new CamundaClientException(message);

        assertThat(error.getMessage()).isEqualTo(message);
    }
}
