package uk.co.mruoc.camunda.client;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.delete.DeleteDeploymentRequest;
import uk.co.mruoc.camunda.client.deploy.delete.DeleteDeploymentRequest.DeleteDeploymentRequestBuilder;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequest;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsResponse;

@RequiredArgsConstructor
public class DeploymentHousekeeping {

    private final CamundaClient client;
    private final DeleteDeploymentRequestBuilder deleteDeploymentRequestBuilder;

    public DeploymentHousekeeping(CamundaClient client) {
        this(client, DeleteDeploymentRequest.builder().cascade(true));
    }

    public void deleteDeploymentsBefore(OffsetDateTime cutoff) {
        Collection<UUID> ids = getDeploymentIdsCreatedBefore(cutoff);
        deleteDeployments(ids);
    }

    private Collection<UUID> getDeploymentIdsCreatedBefore(OffsetDateTime cutoff) {
        GetDeploymentsRequest request =
                GetDeploymentsRequest.builder().before(cutoff).build();
        GetDeploymentsResponse response = client.getDeployments(request);
        return response.getDeploymentIds();
    }

    private void deleteDeployments(Collection<UUID> ids) {
        ids.stream().map(id -> deleteDeploymentRequestBuilder.id(id).build()).forEach(client::deleteDeployment);
    }
}
