package uk.co.mruoc.camunda.client.deploy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.CreateDeploymentResponse.CreateDeploymentResponseBuilder;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateDeploymentResponseMother {

    public static CreateDeploymentResponse build() {
        return builder().build();
    }

    public static CreateDeploymentResponseBuilder builder() {
        return CreateDeploymentResponse.builder()
                .links(LinkMother.buildOne())
                .id(UUID.fromString("27664029-2e70-11ec-8938-0242ac110003"))
                .name("inline-script-demo-deployment")
                .source("local")
                .deploymentTime(OffsetDateTimeParser.parse("2021-10-16T10:59:37.387+0000"))
                .deployedProcessDefinitions(ProcessDefinitionMother.buildOne());
    }

}
