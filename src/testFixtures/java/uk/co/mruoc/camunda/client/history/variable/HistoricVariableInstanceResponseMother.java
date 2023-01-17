package uk.co.mruoc.camunda.client.history.variable;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoricVariableInstanceResponseMother {

    public static HistoricVariableInstancesResponse build() {
        return new HistoricVariableInstancesResponse(
                List.of(HistoricVariableInstanceMother.buildString(), HistoricVariableInstanceMother.buildInt()));
    }
}
