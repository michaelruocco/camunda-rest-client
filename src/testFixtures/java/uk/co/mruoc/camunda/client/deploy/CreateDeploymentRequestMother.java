package uk.co.mruoc.camunda.client.deploy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.CreateDeploymentRequest.CreateDeploymentRequestBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateDeploymentRequestMother {

    public static CreateDeploymentRequest buildInlineScriptDeploymentRequest() {
        return builder()
                .deploymentName("inline-script-demo-deployment")
                .resources(ResourceMother.buildInlineScriptDemoResources())
                .build();
    }

    public static CreateDeploymentRequest buildExternalScriptDeploymentRequest() {
        return builder()
                .deploymentName("external-script-demo-deployment")
                .resources(ResourceMother.buildExternalScriptDemoResources())
                .build();
    }

    public static CreateDeploymentRequestBuilder builder() {
        return CreateDeploymentRequest.builder()
                .deploymentName("demo-deployment")
                .deploymentSource("local");
    }

}
