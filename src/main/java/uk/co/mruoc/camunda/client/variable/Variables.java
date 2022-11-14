package uk.co.mruoc.camunda.client.variable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@JsonSerialize(using = VariablesSerializer.class)
@JsonDeserialize(using = VariablesDeserializer.class)
public class Variables implements Iterable<Variable> {

    private final Collection<Variable> values;

    public Variables(Variable... values) {
        this(List.of(values));
    }

    public Variables(Collection<Variable> values) {
        this.values = values;
    }

    @JsonIgnore
    public Collection<Variable> getValues() {
        return values;
    }

    @JsonIgnore
    public Stream<Variable> stream() {
        return values.stream();
    }

    @Override
    public Iterator<Variable> iterator() {
        return values.iterator();
    }
}
