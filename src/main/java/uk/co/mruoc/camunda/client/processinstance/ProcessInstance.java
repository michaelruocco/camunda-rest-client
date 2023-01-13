package uk.co.mruoc.camunda.client.processinstance;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
public class ProcessInstance {

    private final String id;
    private final String definitionId;
    private final String businessKey;
    private final String caseInstanceId;
    private final Boolean ended;
    private final Boolean suspended;
    private final String tenantId;
}