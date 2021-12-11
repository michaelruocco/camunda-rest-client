package uk.co.mruoc.camunda.client.deploy.create;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class ProcessDefinition {

    private final String id;
    private final String key;
    private final String category;
    private final String description;
    private final String name;
    private final long version;
    private final String resource;
    private final UUID deploymentId;
    private final String diagram;
    private final boolean suspended;
    private final String tenantId;
    private final String versionTag;
    private final Long historyTimeToLive;
    private final boolean startableInTasklist;

}
