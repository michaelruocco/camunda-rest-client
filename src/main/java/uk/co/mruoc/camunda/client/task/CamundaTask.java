package uk.co.mruoc.camunda.client.task;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class CamundaTask {

    private final UUID id;
    private final String name;
    private final String assignee;
    private final OffsetDateTime created;
    private final OffsetDateTime due;
    private final OffsetDateTime followUp;
    private final OffsetDateTime lastUpdated;
    private final CamundaTaskDelegationState delegationState;
    private final String description;
    private final UUID executionId;
    private final String owner;
    private final UUID parentTaskId;
    private final long priority;
    private final String processDefinitionId;
    private final UUID processInstanceId;
    private final String taskDefinitionKey;
    private final String caseExecutionId;
    private final String caseInstanceId;
    private final String caseDefinitionId;
    private final boolean suspended;
    private final String formKey;
    private final String camundaFormRef;
    private final String tenantId;
}
