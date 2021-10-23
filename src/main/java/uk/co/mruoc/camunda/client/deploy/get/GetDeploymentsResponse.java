
package uk.co.mruoc.camunda.client.deploy.get;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
@Data
@JsonDeserialize(using = GetDeploymentsResponseDeserializer.class)
public class GetDeploymentsResponse {

    private final Collection<Deployment> deployments;

}
