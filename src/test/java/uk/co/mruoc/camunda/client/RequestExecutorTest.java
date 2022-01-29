package uk.co.mruoc.camunda.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;

class RequestExecutorTest {

    private final HttpClient client = mock(HttpClient.class);

    private final RequestExecutor executor = new RequestExecutor(client);

    @Test
    void shouldExecuteRequestSuccessfully() throws IOException, InterruptedException {
        HttpRequest request = mock(HttpRequest.class);
        HttpResponse<String> expectedResponse = mock(HttpResponse.class);
        when(client.send(request, HttpResponse.BodyHandlers.ofString())).thenReturn(expectedResponse);

        HttpResponse<String> response = executor.execute(request);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    void shouldThrowCamundaExceptionIfHttpClientThrowsIOException() throws IOException, InterruptedException {
        HttpRequest request = mock(HttpRequest.class);
        doThrow(IOException.class).when(client).send(request, HttpResponse.BodyHandlers.ofString());

        Throwable error = catchThrowable(() -> executor.execute(request));

        assertThat(error).isInstanceOf(CamundaClientException.class).hasCauseInstanceOf(IOException.class);
    }

    @Test
    void shouldThrowCamundaExceptionIfHttpClientThrowsInterruptedException() throws IOException, InterruptedException {
        HttpRequest request = mock(HttpRequest.class);
        doThrow(InterruptedException.class).when(client).send(request, HttpResponse.BodyHandlers.ofString());

        Throwable error = catchThrowable(() -> executor.execute(request));

        assertThat(error).isInstanceOf(CamundaClientException.class).hasCauseInstanceOf(InterruptedException.class);
    }
}
