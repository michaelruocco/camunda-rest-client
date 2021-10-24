package uk.co.mruoc.camunda.client.deploy.get;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetDeploymentsResponseMother {

    public static GetDeploymentsResponse build() {
        return build(DeploymentMother.build());
    }

    public static GetDeploymentsResponse build(Deployment... deployments) {
        return new GetDeploymentsResponse(Arrays.asList(deployments));
    }

}
