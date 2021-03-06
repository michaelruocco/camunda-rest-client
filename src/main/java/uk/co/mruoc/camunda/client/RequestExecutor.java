package uk.co.mruoc.camunda.client;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RequestExecutor {

    private final HttpClient client;

    public RequestExecutor() {
        this(HttpClient.newHttpClient());
    }

    public HttpResponse<String> execute(HttpRequest request) {
        try {
            log.info("sending http request {}", request);
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new CamundaClientException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CamundaClientException(e);
        }
    }
}
