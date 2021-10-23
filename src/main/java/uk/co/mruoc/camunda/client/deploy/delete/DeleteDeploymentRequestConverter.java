package uk.co.mruoc.camunda.client.deploy.delete;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.RequestConverter;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;
import uk.co.mruoc.camunda.client.header.NoopHeaderPopulator;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

@RequiredArgsConstructor
public class DeleteDeploymentRequestConverter implements RequestConverter {

    private final String defaultBaseUri;
    private final HeaderPopulator headerPopulator;

    public DeleteDeploymentRequestConverter(String defaultBaseUri) {
        this(defaultBaseUri, defaultHeaderPopulator());
    }

    @Override
    public Optional<HttpRequest> apply(Object object) {
        return toRequest(object).map(this::toHttpRequest);
    }

    private Optional<DeleteDeploymentRequest> toRequest(Object object) {
        if (object instanceof DeleteDeploymentRequest) {
            return Optional.of((DeleteDeploymentRequest) object);
        }
        return Optional.empty();
    }

    private HttpRequest toHttpRequest(DeleteDeploymentRequest request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        headerPopulator.populate(builder);
        String baseUri = request.getOverrideBaseUri().orElse(defaultBaseUri);
        return builder.uri(URI.create(String.format("%s/engine-rest/deployment/%s", baseUri, request.getId())))
                .DELETE()
                .build();
    }

    private static HeaderPopulator defaultHeaderPopulator() {
        return new NoopHeaderPopulator();
    }

}
