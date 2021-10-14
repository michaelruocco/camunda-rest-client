package uk.co.mruoc.camunda.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.camunda.client.deployment.CreateDeploymentRequest;
import uk.co.mruoc.camunda.client.deployment.CreateDeploymentRequestConverter;
import uk.co.mruoc.camunda.client.process.StartProcessRequest;
import uk.co.mruoc.camunda.client.process.StartProcessRequestConverter;
import uk.co.mruoc.json.JsonConverter;
import uk.co.mruoc.json.jackson.JacksonJsonConverter;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Builder
@RequiredArgsConstructor
@Slf4j
public class CamundaClient {

    private final String baseUri;
    private final HttpClient client;
    private final Collection<RequestConverter> requestConverters;

    public CamundaClient(String baseUri) {
        this(baseUri, new JacksonJsonConverter(new ObjectMapper()));
    }

    public CamundaClient(String baseUri, JsonConverter jsonConverter) {
        this(baseUri, buildConverters(baseUri, jsonConverter));
    }

    public CamundaClient(String baseUri, Collection<RequestConverter> requestConverters) {
        this(baseUri, HttpClient.newHttpClient(), requestConverters);
    }

    private static Collection<RequestConverter> buildConverters(String baseUri, JsonConverter jsonConverter) {
        return Arrays.asList(
                new StartProcessRequestConverter(baseUri, jsonConverter),
                new CreateDeploymentRequestConverter(baseUri)
        );
    }

    public void createDeployment(CreateDeploymentRequest request) {
        var httpRequest = toHttpRequest(request);
        var response = send(httpRequest);
        throwErrorIfNotSuccessful(response);
    }

    public void startProcess(StartProcessRequest request) {
        var httpRequest = toHttpRequest(request);
        var response = send(httpRequest);
        throwErrorIfNotSuccessful(response);
    }

    private HttpRequest toHttpRequest(Object request) {
        return requestConverters.stream()
                .map(converter -> converter.toHttpRequest(request))
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new RequestNotSupportedException(request));
    }

    private HttpResponse<String> send(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new CamundaClientException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CamundaClientException(e);
        }
    }

    private void throwErrorIfNotSuccessful(HttpResponse<String> response) {
        var body = response.body();
        if (!isSuccessful(response)) {
            throw new CamundaClientException(body);
        }
        log.info(body);
    }

    private boolean isSuccessful(HttpResponse<String> response) {
        var status = response.statusCode();
        return status >= 200 && status <= 299;
    }

}

