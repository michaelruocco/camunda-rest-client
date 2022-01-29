package uk.co.mruoc.camunda.client;

public class RequestNotSupportedException extends CamundaClientException {

    public RequestNotSupportedException(Object request) {
        super(String.format(
                "request of type %s is not supported", request.getClass().getName()));
    }
}
