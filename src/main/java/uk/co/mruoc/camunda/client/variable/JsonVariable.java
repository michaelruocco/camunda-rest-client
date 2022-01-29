package uk.co.mruoc.camunda.client.variable;

import org.camunda.spin.json.SpinJsonNode;

public class JsonVariable extends AbstractVariable {

    public JsonVariable(String name, SpinJsonNode value) {
        this(name, value.toString());
    }

    public JsonVariable(String name, String value) {
        super(name, value, "json");
    }
}
