package uk.co.mruoc.camunda.client;

public class CamundaClientException extends RuntimeException {

    public CamundaClientException(Throwable cause) {
        super(cause);
    }

    public CamundaClientException(String message) {
        super(message);
    }
}
