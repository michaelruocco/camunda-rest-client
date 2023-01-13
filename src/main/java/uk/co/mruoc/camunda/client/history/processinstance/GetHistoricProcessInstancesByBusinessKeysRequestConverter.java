package uk.co.mruoc.camunda.client.history.processinstance;

import static uk.co.mruoc.camunda.client.header.HeaderConstants.ACCEPT_NAME;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.APPLICATION_JSON;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.CONTENT_TYPE_NAME;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.RequestConverter;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;
import uk.co.mruoc.camunda.client.header.NoopHeaderPopulator;
import uk.co.mruoc.json.JsonConverter;

@RequiredArgsConstructor
public class GetHistoricProcessInstancesByBusinessKeysRequestConverter implements RequestConverter {

    private final String baseUri;
    private final JsonConverter jsonConverter;
    private final HeaderPopulator headerPopulator;

    public GetHistoricProcessInstancesByBusinessKeysRequestConverter(String baseUri, JsonConverter jsonConverter) {
        this(baseUri, jsonConverter, defaultHeaderPopulator());
    }

    @Override
    public Optional<HttpRequest> apply(Object object) {
        return toRequest(object).map(this::toHttpRequest);
    }

    private Optional<GetHistoricProcessInstancesByBusinessKeysRequest> toRequest(Object object) {
        if (object instanceof GetHistoricProcessInstancesByBusinessKeysRequest) {
            return Optional.of((GetHistoricProcessInstancesByBusinessKeysRequest) object);
        }
        return Optional.empty();
    }

    private HttpRequest toHttpRequest(GetHistoricProcessInstancesByBusinessKeysRequest request) {
        String body = jsonConverter.toJson(request);
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        headerPopulator.populate(builder);
        return builder.header(CONTENT_TYPE_NAME, APPLICATION_JSON)
                .header(ACCEPT_NAME, APPLICATION_JSON)
                .uri(getUri())
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
    }

    private URI getUri() {
        String uri = String.format("%s/engine-rest/history/process-instance", baseUri);
        return URI.create(uri);
    }

    private static HeaderPopulator defaultHeaderPopulator() {
        return new NoopHeaderPopulator();
    }
}
