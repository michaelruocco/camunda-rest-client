package uk.co.mruoc.camunda.client.deploy.create;

import static uk.co.mruoc.camunda.client.header.HeaderConstants.ACCEPT_NAME;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.APPLICATION_JSON;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.CONTENT_TYPE_NAME;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.RequestConverter;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;
import uk.co.mruoc.camunda.client.header.NoopHeaderPopulator;

@RequiredArgsConstructor
public class CreateDeploymentRequestConverter implements RequestConverter {

    private final String defaultBaseUri;
    private final HeaderPopulator headerPopulator;

    public CreateDeploymentRequestConverter(String defaultBaseUri) {
        this(defaultBaseUri, defaultHeaderPopulator());
    }

    @Override
    public Optional<HttpRequest> apply(Object object) {
        return toRequest(object).map(this::toHttpRequest);
    }

    private Optional<CreateDeploymentRequest> toRequest(Object object) {
        if (object instanceof CreateDeploymentRequest request) {
            return Optional.of(request);
        }
        return Optional.empty();
    }

    private HttpRequest toHttpRequest(CreateDeploymentRequest request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        headerPopulator.populate(builder);
        MultipartBodyPublisher body = toBody(request);
        return builder.header(CONTENT_TYPE_NAME, body.mediaType().toString())
                .header(ACCEPT_NAME, APPLICATION_JSON)
                .uri(toUri(request))
                .POST(body)
                .build();
    }

    private MultipartBodyPublisher toBody(CreateDeploymentRequest request) {
        var bodyBuilder = MultipartBodyPublisher.newBuilder()
                .textPart("deployment-name", request.getDeploymentName())
                .textPart("enable-duplicate-filtering", request.isEnableDuplicateFiltering())
                .textPart("deploy-changed-only", request.isDeployChangedOnly())
                .textPart("deployment-source", request.getDeploymentSource());
        request.getTenantId().ifPresent(id -> bodyBuilder.textPart("tenant-id", id));
        request.getResources().forEach(resource -> append(bodyBuilder, resource));
        return bodyBuilder.build();
    }

    private URI toUri(CreateDeploymentRequest request) {
        String baseUri = request.getOverrideBaseUri().orElse(defaultBaseUri);
        return URI.create(String.format("%s/engine-rest/deployment/create", baseUri));
    }

    private static void append(MultipartBodyPublisher.Builder builder, Resource resource) {
        try {
            builder.filePart(resource.getName(), resource.getFile());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static HeaderPopulator defaultHeaderPopulator() {
        return new NoopHeaderPopulator();
    }
}
