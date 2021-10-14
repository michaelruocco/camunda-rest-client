package uk.co.mruoc.camunda.client.process.variable;

public class StringVariable extends AbstractVariable {

    public StringVariable(String name, String value) {
        super(name, value, "string");
    }

}
