package uk.co.mruoc.camunda.client.header;

import java.net.http.HttpRequest;
import java.util.UUID;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CorrelationIdHeaderPopulator implements HeaderPopulator {

    private final Supplier<String> idSupplier;

    public CorrelationIdHeaderPopulator() {
        this(() -> UUID.randomUUID().toString());
    }

    @Override
    public HttpRequest.Builder populate(HttpRequest.Builder builder) {
        builder.header("correlation-id", idSupplier.get());
        return builder;
    }
}
