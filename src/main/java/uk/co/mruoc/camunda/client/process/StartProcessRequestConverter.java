package uk.co.mruoc.camunda.client.process;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.RequestConverter;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;
import uk.co.mruoc.camunda.client.header.NoopHeaderPopulator;
import uk.co.mruoc.json.JsonConverter;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

import static uk.co.mruoc.camunda.client.header.HeaderConstants.ACCEPT_NAME;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.APPLICATION_JSON;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.CONTENT_TYPE_NAME;

@RequiredArgsConstructor
public class StartProcessRequestConverter implements RequestConverter {

    private final String baseUri;
    private final JsonConverter jsonConverter;
    private final HeaderPopulator headerPopulator;

    public StartProcessRequestConverter(String baseUri, JsonConverter jsonConverter) {
        this(baseUri, jsonConverter, defaultHeaderPopulator());
    }

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
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        headerPopulator.populate(builder);
        return builder.header(CONTENT_TYPE_NAME, APPLICATION_JSON)
                .header(ACCEPT_NAME, APPLICATION_JSON)
                .uri(toUri(request))
                .POST(toBody(request))
                .build();
    }

    private URI toUri(StartProcessRequest request) {
        String uri = String.format("%s/engine-rest/process-definition/key/%s/start", baseUri, request.getProcessDefinitionKey());
        return URI.create(uri);
    }

    private HttpRequest.BodyPublisher toBody(StartProcessRequest request) {
        return HttpRequest.BodyPublishers.ofString(jsonConverter.toJson(request));
    }

    private static HeaderPopulator defaultHeaderPopulator() {
        return new NoopHeaderPopulator();
    }

}
