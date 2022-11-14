package uk.co.mruoc.camunda.client.variable;

import uk.co.mruoc.camunda.client.CamundaClientException;

public class VariableTypeUnsupportedException extends CamundaClientException {

    public VariableTypeUnsupportedException(String message) {
        super(message);
    }
}
