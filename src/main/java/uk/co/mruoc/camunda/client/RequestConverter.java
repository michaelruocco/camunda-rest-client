package uk.co.mruoc.camunda.client;

import java.net.http.HttpRequest;
import java.util.Optional;
import java.util.function.Function;

public interface RequestConverter extends Function<Object, Optional<HttpRequest>> {

    default Optional<HttpRequest> toHttpRequest(Object request) {
        return apply(request);
    }

}
