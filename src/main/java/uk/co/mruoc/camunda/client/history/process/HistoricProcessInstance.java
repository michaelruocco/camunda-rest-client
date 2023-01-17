package uk.co.mruoc.camunda.client.history.process;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricProcessInstance {

    private final String id;
    private final String businessKey;
    private final String processDefinitionId;
    private final String processDefinitionKey;
    private final String processDefinitionName;
    private final Integer processDefinitionVersion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime endTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime removalTime;

    private final Long durationInMillis;
    private final String startUserId;
    private final String startActivityId;
    private final String deleteReason;
    private final String rootProcessInstanceId;
    private final String superProcessInstanceId;
    private final String superCaseInstanceId;
    private final String caseInstanceId;
    private final String tenantId;
    private final String state;
}
