package uk.co.mruoc.camunda.client.deploy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;

@Builder
@RequiredArgsConstructor
@Data
@JsonDeserialize(using = CreateDeploymentResponseDeserializer.class)
@JsonSerialize(using = CreateDeploymentResponseSerializer.class)
public class CreateDeploymentResponse {

    private final Collection<Link> links;
    private final UUID id;
    private final String name;
    private final String source;
    private final OffsetDateTime deploymentTime;
    private final String tenantId;
    private final Collection<ProcessDefinition> deployedProcessDefinitions;
    private final Collection<Object> deployedCaseDefinitions;
    private final Collection<Object> deployedDecisionDefinitions;
    private final Collection<Object> deployedDecisionRequirementsDefinitions;

    public String getFirstDeployedProcessDefinitionKey() {
        return deployedProcessDefinitions.stream().findFirst()
                .map(ProcessDefinition::getKey)
                .orElseThrow(NoProcessDefinitionsDeployedException::new);
    }

}
