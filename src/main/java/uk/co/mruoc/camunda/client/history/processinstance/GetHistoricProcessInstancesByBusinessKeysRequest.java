package uk.co.mruoc.camunda.client.history.processinstance;

import java.util.Collection;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class GetHistoricProcessInstancesByBusinessKeysRequest {

    private final Collection<String> processInstanceBusinessKeyIn;
    private final Collection<String> processDefinitionKeyNotIn;
    private final String processDefinitionKey;
}
