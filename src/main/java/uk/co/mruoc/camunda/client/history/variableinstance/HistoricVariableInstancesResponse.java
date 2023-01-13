package uk.co.mruoc.camunda.client.history.variableinstance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class HistoricVariableInstancesResponse<T> implements Iterable<HistoricVariableInstance<T>> {

    private final Collection<HistoricVariableInstance<T>> values;

    @JsonCreator
    public HistoricVariableInstancesResponse(Collection<HistoricVariableInstance<T>> values) {
        this.values = values;
    }

    @Override
    public Iterator<HistoricVariableInstance<T>> iterator() {
        return values.iterator();
    }

    @JsonIgnore
    public Map<String, T> getProcessInstanceIdToValueMap() {
        return values.stream()
                .collect(Collectors.toMap(
                        HistoricVariableInstance::getProcessInstanceId, HistoricVariableInstance::getValue));
    }
}
