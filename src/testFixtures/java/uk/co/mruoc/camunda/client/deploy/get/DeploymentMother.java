package uk.co.mruoc.camunda.client.deploy.get;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.LinkMother;
import uk.co.mruoc.camunda.client.deploy.create.OffsetDateTimeParser;
import uk.co.mruoc.camunda.client.deploy.get.Deployment.DeploymentBuilder;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeploymentMother {

    public static Deployment build() {
        return builder().build();
    }

    public static Deployment withId(UUID id) {
        return builder().id(id).build();
    }

    public static DeploymentBuilder builder() {
        return Deployment.builder()
                .id(UUID.fromString("27664029-2e70-11ec-8938-0242ac110003"))
                .name("test-deployment")
                .source("local")
                .tenantId("test-tenant-id")
                .deploymentTime(OffsetDateTimeParser.parse("2021-10-16T10:59:37.387+0000"))
                .links(LinkMother.buildOne());
    }

}
