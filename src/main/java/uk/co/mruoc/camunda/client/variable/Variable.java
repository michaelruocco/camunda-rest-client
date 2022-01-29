package uk.co.mruoc.camunda.client.variable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Variable {

    @JsonIgnore
    String getName();

    Object getValue();

    String getType();
}
