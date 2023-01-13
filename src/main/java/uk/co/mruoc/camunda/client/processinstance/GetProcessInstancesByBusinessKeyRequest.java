package uk.co.mruoc.camunda.client.processinstance;

import java.util.Collection;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class GetProcessInstancesByBusinessKeyRequest {

    private final String businessKey;
    private final Collection<String> processDefinitionKeyNotIn;
}
