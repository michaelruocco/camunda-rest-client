package uk.co.mruoc.camunda.client.processinstance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class ProcessInstancesResponse implements Iterable<ProcessInstance> {

    private final Collection<ProcessInstance> values;

    @JsonCreator
    public ProcessInstancesResponse(Collection<ProcessInstance> values) {
        this.values = values;
    }

    @Override
    public Iterator<ProcessInstance> iterator() {
        return values.iterator();
    }

    @JsonIgnore
    public Map<String, String> getBusinessKeyToProcessInstanceIdMap() {
        return values.stream().collect(Collectors.toMap(ProcessInstance::getBusinessKey, ProcessInstance::getId));
    }
}
