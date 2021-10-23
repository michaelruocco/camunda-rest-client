package uk.co.mruoc.camunda.client.deploy;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Builder
@Data
public class CreateDeploymentRequest {

    private final String deploymentName;
    private final boolean enableDuplicateFiltering;
    private final boolean deployChangedOnly;
    private final String deploymentSource;
    private final String tenantId;
    @Builder.Default
    private final Collection<Resource> resources = Collections.emptyList();

    private final String overrideBaseUri;

    public Optional<String> getTenantId() {
        return Optional.ofNullable(tenantId);
    }

    public Optional<String> getOverrideBaseUri() {
        return Optional.ofNullable(overrideBaseUri);
    }

}
