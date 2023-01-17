package uk.co.mruoc.camunda.client.history.variable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Iterator;
import lombok.Data;

@Data
public class HistoricVariableInstancesResponse implements Iterable<HistoricVariableInstance> {

    @JsonIgnore
    private final Collection<HistoricVariableInstance> values;

    @JsonCreator
    public HistoricVariableInstancesResponse(Collection<HistoricVariableInstance> values) {
        this.values = values;
    }

    @Override
    public Iterator<HistoricVariableInstance> iterator() {
        return values.iterator();
    }
}
