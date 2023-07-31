package uk.co.mruoc.camunda.client.process.get.variable;

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
public class GetVariableInstanceRequestConverter implements RequestConverter {

    private final String baseUri;
    private final JsonConverter jsonConverter;
    private final HeaderPopulator headerPopulator;

    public GetVariableInstanceRequestConverter(String baseUri, JsonConverter jsonConverter) {
        this(baseUri, jsonConverter, defaultHeaderPopulator());
    }

    @Override
    public Optional<HttpRequest> apply(Object object) {
        return toRequest(object).map(this::toHttpRequest);
    }

    private Optional<GetVariableInstanceRequest> toRequest(Object object) {
        if (object instanceof GetVariableInstanceRequest request) {
            return Optional.of(request);
        }
        return Optional.empty();
    }

    private HttpRequest toHttpRequest(GetVariableInstanceRequest request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        headerPopulator.populate(builder);
        return builder.header(CONTENT_TYPE_NAME, APPLICATION_JSON)
                .header(ACCEPT_NAME, APPLICATION_JSON)
                .uri(getUri(request))
                .GET()
                .build();
    }

    private URI getUri(GetVariableInstanceRequest request) {
        String uri = String.format(
                "%s/engine-rest/process-instance/%s/variables/%s?deserializeValue=%s",
                baseUri, request.getProcessInstanceId(), request.getVariableName(), request.isDeserializeValue());
        return URI.create(uri);
    }

    private static HeaderPopulator defaultHeaderPopulator() {
        return new NoopHeaderPopulator();
    }
}
