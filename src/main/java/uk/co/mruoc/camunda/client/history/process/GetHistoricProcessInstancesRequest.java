package uk.co.mruoc.camunda.client.history.process;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collection;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class GetHistoricProcessInstancesRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Collection<String> processInstanceBusinessKeyIn;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Collection<String> processDefinitionKeyNotIn;
}
