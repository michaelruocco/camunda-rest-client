package uk.co.mruoc.camunda.client.task;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Task {

    private final UUID id;
    private final String name;
    private final String assignee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime due;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime followUp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime lastUpdated;

    private final TaskDelegationState delegationState;
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
