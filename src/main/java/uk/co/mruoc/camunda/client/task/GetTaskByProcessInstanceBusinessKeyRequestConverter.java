package uk.co.mruoc.camunda.client.task;

import static uk.co.mruoc.camunda.client.header.HeaderConstants.ACCEPT_NAME;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.APPLICATION_JSON;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.RequestConverter;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;
import uk.co.mruoc.camunda.client.header.NoopHeaderPopulator;

@RequiredArgsConstructor
public class GetTaskByProcessInstanceBusinessKeyRequestConverter implements RequestConverter {

    private final String baseUri;
    private final HeaderPopulator headerPopulator;

    public GetTaskByProcessInstanceBusinessKeyRequestConverter(String baseUri) {
        this(baseUri, defaultHeaderPopulator());
    }

    @Override
    public Optional<HttpRequest> apply(Object object) {
        return toRequest(object).map(this::toHttpRequest);
    }

    private Optional<GetTaskByProcessInstanceBusinessKeyRequest> toRequest(Object object) {
        if (object instanceof GetTaskByProcessInstanceBusinessKeyRequest request) {
            return Optional.of(request);
        }
        return Optional.empty();
    }

    private HttpRequest toHttpRequest(GetTaskByProcessInstanceBusinessKeyRequest request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        headerPopulator.populate(builder);
        return builder.header(ACCEPT_NAME, APPLICATION_JSON)
                .uri(toUri(request))
                .GET()
                .build();
    }

    private URI toUri(GetTaskByProcessInstanceBusinessKeyRequest request) {
        String uri =
                String.format("%s/engine-rest/task?processInstanceBusinessKey=%s", baseUri, request.getBusinessKey());
        return URI.create(uri);
    }

    private static HeaderPopulator defaultHeaderPopulator() {
        return new NoopHeaderPopulator();
    }
}
