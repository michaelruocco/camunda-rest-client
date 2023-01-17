package uk.co.mruoc.camunda.client.history.variable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricVariableInstance {

    private final String id;
    private final String name;
    private final String type;
    private final Object value;
    private final ValueInfo valueInfo;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime removalTime;

    private final String rootProcessInstanceId;
}
