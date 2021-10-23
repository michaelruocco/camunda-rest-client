package uk.co.mruoc.camunda.client.deploy.create;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.RequestConverter;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;
import uk.co.mruoc.camunda.client.header.NoopHeaderPopulator;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

import static uk.co.mruoc.camunda.client.header.HeaderConstants.ACCEPT_NAME;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.APPLICATION_JSON;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.CONTENT_TYPE_NAME;

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
        if (object instanceof CreateDeploymentRequest) {
            return Optional.of((CreateDeploymentRequest) object);
        }
        return Optional.empty();
    }

    private HttpRequest toHttpRequest(CreateDeploymentRequest request) {
        MultipartBodyPublisher body = toBody(request);
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        headerPopulator.populate(builder);
        String baseUri = request.getOverrideBaseUri().orElse(defaultBaseUri);
        return builder.header(CONTENT_TYPE_NAME, body.mediaType().toString())
                .header(ACCEPT_NAME, APPLICATION_JSON)
                .uri(URI.create(String.format("%s/engine-rest/deployment/create", baseUri)))
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
