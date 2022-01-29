package uk.co.mruoc.camunda.client.deploy.create;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProcessDefinitionMother {

    public static Collection<ProcessDefinition> buildOne() {
        return Collections.singleton(build());
    }

    public static ProcessDefinition build() {
        return ProcessDefinition.builder()
                .id("276cf6eb-2e70-11ec-8938-0242ac110003")
                .key("inline-script-demo-process")
                .category("http://bpmn.io/schema/bpmn")
                .name("inline-script-demo-process")
                .version(1)
                .resource("inline-script-demo.bpmn")
                .deploymentId(UUID.fromString("27664029-2e70-11ec-8938-0242ac110003"))
                .startableInTasklist(true)
                .build();
    }
}
