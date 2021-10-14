package uk.co.mruoc.camunda.client.deployment;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.RequestConverter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

@RequiredArgsConstructor
public class CreateDeploymentRequestConverter implements RequestConverter {

    private final String baseUri;

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
        return HttpRequest.newBuilder()
                .header("Content-Type", body.mediaType().toString())
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

}
