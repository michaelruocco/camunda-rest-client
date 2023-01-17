package uk.co.mruoc.camunda.client.process.get;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
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

    @JsonIgnore
    public int getNumberOfProcessInstances() {
        return values.size();
    }

    @JsonIgnore
    public Optional<ProcessInstance> getFirstProcessInstance() {
        return values.stream().findFirst();
    }
}
