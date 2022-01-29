package uk.co.mruoc.camunda.client.header;

import java.net.http.HttpRequest;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationHeaderPopulator implements HeaderPopulator {

    private final Supplier<String> tokenSupplier;

    @Override
    public HttpRequest.Builder populate(HttpRequest.Builder builder) {
        builder.header("authorization", tokenSupplier.get());
        return builder;
    }
}
