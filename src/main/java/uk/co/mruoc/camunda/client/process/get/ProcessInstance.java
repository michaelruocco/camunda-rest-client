package uk.co.mruoc.camunda.client.process.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessInstance {

    private final String id;
    private final String definitionId;
    private final String businessKey;
    private final String caseInstanceId;
    private final Boolean ended;
    private final Boolean suspended;
    private final String tenantId;
}
