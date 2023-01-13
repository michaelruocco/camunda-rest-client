package uk.co.mruoc.camunda.client.history.variableinstance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
@Builder
@JsonDeserialize(using = HistoricVariableInstanceDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricVariableInstance<T> {

    private final String id;
    private final String name;
    private final String type;
    private final T value;
    private final String processDefinitionKey;
    private final String processDefinitionId;
    private final String processInstanceId;
    private final String executionId;
    private final String activityInstanceId;
    private final String caseDefinitionKey;
    private final String caseDefinitionId;
    private final String caseInstanceId;
    private final String caseExecutionId;
    private final String taskId;
    private final String tenantId;
    private final String errorMessage;
    private final String state;
    private final String createTime;
    private final String removalTime;
    private final String rootProcessInstanceId;
}
