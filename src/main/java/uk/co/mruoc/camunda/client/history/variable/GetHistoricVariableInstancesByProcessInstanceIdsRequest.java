package uk.co.mruoc.camunda.client.history.variable;

import java.util.Collection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
@Data
public class GetHistoricVariableInstancesByProcessInstanceIdsRequest {

    private final Collection<String> processInstanceIdIn;
    private final Collection<String> variableNameIn;
    private final boolean deserializeValues;
}
