package uk.co.mruoc.camunda.client.process;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.RequestConverter;
import uk.co.mruoc.json.JsonConverter;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

@RequiredArgsConstructor
public class StartProcessRequestConverter implements RequestConverter {

    private final String baseUri;
    private final JsonConverter jsonConverter;

    @Override
    public Optional<HttpRequest> apply(Object object) {
        return toRequest(object).map(this::toHttpRequest);
    }

    private Optional<StartProcessRequest> toRequest(Object object) {
        if (object instanceof StartProcessRequest) {
            return Optional.of((StartProcessRequest) object);
        }
        return Optional.empty();
    }

    private HttpRequest toHttpRequest(StartProcessRequest request) {
        HttpRequest.BodyPublisher body = toBody(request);
        String uri = String.format("%s/engine-rest/process-definition/key/%s/start", baseUri, request.getProcessKey());
        return HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(uri))
                .POST(body)
                .build();
    }

    public HttpRequest.BodyPublisher toBody(StartProcessRequest request) {
        return HttpRequest.BodyPublishers.ofString(jsonConverter.toJson(request));
    }

}
