package uk.co.mruoc.camunda.client.deploy.delete;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.delete.DeleteDeploymentRequest.DeleteDeploymentRequestBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteDeploymentsRequestMother {

    public static DeleteDeploymentRequest build() {
        return builder().build();
    }

    public static DeleteDeploymentRequest withQueryStringArgs() {
        return builder().cascade(true).skipCustomListeners(true).build();
    }

    public static DeleteDeploymentRequestBuilder builder() {
        return DeleteDeploymentRequest.builder().id(UUID.fromString("27664029-2e70-11ec-8938-0242ac110003"));
    }
}
