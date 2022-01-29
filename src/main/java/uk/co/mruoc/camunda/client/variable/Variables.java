package uk.co.mruoc.camunda.client.variable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Arrays;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Variables {

    private final Collection<Variable> values;

    public Variables(Variable... values) {
        this(Arrays.asList(values));
    }

    @JsonIgnore
    public Collection<Variable> getValues() {
        return values;
    }
}
