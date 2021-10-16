package uk.co.mruoc.camunda.client.deployment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateDeploymentRequestMother {

    public static CreateDeploymentRequest buildInlineScriptDeploymentRequest() {
        return CreateDeploymentRequest.builder()
                .deploymentName("inline-script-demo-deployment")
                .deploymentSource("local")
                .resources(ResourceMother.buildInlineScriptDemoResources())
                .build();
    }

    public static CreateDeploymentRequest buildExternalScriptDeploymentRequest() {
        return CreateDeploymentRequest.builder()
                .deploymentName("external-script-demo-deployment")
                .deploymentSource("local")
                .resources(ResourceMother.buildExternalScriptDemoResources())
                .build();
    }

}
