package uk.co.mruoc.camunda.client.processinstance;

import java.util.Collection;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GetProcessInstancesByBusinessKeyRequest {

    private final String businessKey;
    private final Collection<String> processDefinitionKeyNotIn;
}
