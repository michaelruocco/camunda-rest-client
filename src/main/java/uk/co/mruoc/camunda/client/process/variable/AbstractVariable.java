package uk.co.mruoc.camunda.client.process.variable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public abstract class AbstractVariable implements Variable {

    private final String name;
    private final Object value;
    private final String type;

}
