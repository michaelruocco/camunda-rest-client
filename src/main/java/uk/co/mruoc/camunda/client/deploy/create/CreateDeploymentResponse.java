package uk.co.mruoc.camunda.client.deploy.create;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.Link;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

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
        return Optional.ofNullable(deployedProcessDefinitions)
                .map(Collection::stream)
                .flatMap(Stream::findFirst)
                .map(ProcessDefinition::getKey)
                .orElseThrow(NoProcessDefinitionsDeployedException::new);
    }

}
