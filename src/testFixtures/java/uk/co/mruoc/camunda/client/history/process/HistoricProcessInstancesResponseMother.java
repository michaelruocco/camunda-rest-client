package uk.co.mruoc.camunda.client.history.process;

import java.util.Collections;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoricProcessInstancesResponseMother {

    public static HistoricProcessInstancesResponse build() {
        return new HistoricProcessInstancesResponse(Collections.singleton(HistoricProcessInstanceMother.build()));
    }
}
