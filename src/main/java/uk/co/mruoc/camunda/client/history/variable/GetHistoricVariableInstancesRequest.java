package uk.co.mruoc.camunda.client.history.variable;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
@Data
public class GetHistoricVariableInstancesRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Collection<String> processInstanceIdIn;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String variableName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Collection<String> variableNameIn;

    private final boolean deserializeValues;
}
