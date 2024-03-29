package uk.co.mruoc.camunda.client.deploy.create;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequest.CreateDeploymentRequestBuilder;

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

    public static CreateDeploymentRequest buildMessageDemoScriptDeploymentRequest() {
        return builder()
                .deploymentName("message-demo-deployment")
                .resources(ResourceMother.buildInlineScriptResources("message-demo.bpmn"))
                .build();
    }

    public static CreateDeploymentRequest buildMessageWithUserTaskDemoScriptDeploymentRequest() {
        return builder()
                .deploymentName("message-with-user-task-demo-deployment")
                .resources(ResourceMother.buildInlineScriptResources("message-with-user-task-demo.bpmn"))
                .build();
    }

    public static CreateDeploymentRequestBuilder builder() {
        return CreateDeploymentRequest.builder()
                .deploymentName("demo-deployment")
                .deploymentSource("local");
    }
}
