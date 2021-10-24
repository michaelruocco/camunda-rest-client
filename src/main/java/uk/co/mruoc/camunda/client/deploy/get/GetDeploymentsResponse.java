
package uk.co.mruoc.camunda.client.deploy.get;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
@JsonDeserialize(using = GetDeploymentsResponseDeserializer.class)
public class GetDeploymentsResponse {

    private final Collection<Deployment> deployments;

    public int getNumberOfDeployments() {
        return deployments.size();
    }

    public Collection<UUID> getDeploymentIds() {
        return deployments.stream()
                .map(Deployment::getId)
                .collect(Collectors.toList());
    }

}
