package uk.co.mruoc.camunda.client.deploy.delete;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Data
public class DeleteDeploymentRequest {

    private final UUID id;
    private final String overrideBaseUri;

    public DeleteDeploymentRequest(UUID id) {
        this(id, null);
    }

    public Optional<String> getOverrideBaseUri() {
        return Optional.ofNullable(overrideBaseUri);
    }

}
