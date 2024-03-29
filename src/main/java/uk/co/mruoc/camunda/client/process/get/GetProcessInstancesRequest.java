package uk.co.mruoc.camunda.client.process.get;

import java.util.Collection;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class GetProcessInstancesRequest {

    private final String businessKey;
    private final Collection<String> processDefinitionKeyNotIn;
    private final String processDefinitionKey;
}
