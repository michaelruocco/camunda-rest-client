package uk.co.mruoc.camunda.client.history.processinstance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class HistoricProcessInstancesResponse implements Iterable<HistoricProcessInstance> {

    private final Collection<HistoricProcessInstance> values;

    @JsonCreator
    public HistoricProcessInstancesResponse(Collection<HistoricProcessInstance> values) {
        this.values = values;
    }

    @Override
    public Iterator<HistoricProcessInstance> iterator() {
        return values.iterator();
    }

    @JsonIgnore
    public Map<String, String> getBusinessKeyToProcessInstanceIdMap() {
        return values.stream()
                .collect(Collectors.toMap(HistoricProcessInstance::getBusinessKey, HistoricProcessInstance::getId));
    }
}
