package uk.co.mruoc.camunda.client.history.processinstance;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
public class HistoricProcessInstance {

    private final String id;
    private final String rootProcessInstanceId;
    private final String superProcessInstanceId;
    private final String superCaseInstanceId;
    private final String caseInstanceId;
    private final String processDefinitionName;
    private final String processDefinitionKey;
    private final Integer processDefinitionVersion;
    private final String processDefinitionId;
    private final String businessKey;
    private final String startTime;
    private final String endTime;
    private final String removalTime;
    private final String startUserId;
    private final String startActivityId;
    private final String deleteReason;
    private final String tenantId;
}
