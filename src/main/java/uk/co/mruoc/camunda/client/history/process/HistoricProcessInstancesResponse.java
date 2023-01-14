package uk.co.mruoc.camunda.client.history.process;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Iterator;
import lombok.Data;

@Data
public class HistoricProcessInstancesResponse implements Iterable<HistoricProcessInstance> {

    @JsonIgnore
    private final Collection<HistoricProcessInstance> values;

    @JsonCreator
    public HistoricProcessInstancesResponse(Collection<HistoricProcessInstance> values) {
        this.values = values;
    }

    @Override
    public Iterator<HistoricProcessInstance> iterator() {
        return values.iterator();
    }
}
