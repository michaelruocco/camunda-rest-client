package uk.co.mruoc.camunda.client.history.variableinstance;

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
    private final String variableNameIn;
    private final boolean deserializeValues = false;
}
