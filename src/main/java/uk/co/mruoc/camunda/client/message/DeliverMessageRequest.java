package uk.co.mruoc.camunda.client.message;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.variable.Variables;

@Builder
@RequiredArgsConstructor
@Data
public class DeliverMessageRequest {

    private final String messageName;
    private final String businessKey;
    private final Variables correlationKeys;
    private final Variables processVariables;
}
