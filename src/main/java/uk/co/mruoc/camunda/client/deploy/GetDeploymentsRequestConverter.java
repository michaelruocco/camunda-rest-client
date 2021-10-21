package uk.co.mruoc.camunda.client.deploy;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.RequestConverter;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;
import uk.co.mruoc.camunda.client.header.NoopHeaderPopulator;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

import static uk.co.mruoc.camunda.client.header.HeaderConstants.ACCEPT_NAME;
import static uk.co.mruoc.camunda.client.header.HeaderConstants.APPLICATION_JSON;

@RequiredArgsConstructor
public class GetDeploymentsRequestConverter implements RequestConverter {

    private final String baseUri;
    private final HeaderPopulator headerPopulator;

    public GetDeploymentsRequestConverter(String baseUri) {
        this(baseUri, defaultHeaderPopulator());
    }

    @Override
    public Optional<HttpRequest> apply(Object object) {
        return toRequest(object).map(this::toHttpRequest);
    }

    private Optional<GetDeploymentsRequest> toRequest(Object object) {
        if (object instanceof GetDeploymentsRequest) {
            return Optional.of((GetDeploymentsRequest) object);
        }
        return Optional.empty();
    }

    private HttpRequest toHttpRequest(GetDeploymentsRequest request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        headerPopulator.populate(builder);
        return builder.header(ACCEPT_NAME, APPLICATION_JSON)
                .uri(URI.create(String.format("%s/engine-rest/deployment%s", baseUri, request.toQueryString())))
                .GET()
                .build();
    }

    private static HeaderPopulator defaultHeaderPopulator() {
        return new NoopHeaderPopulator();
    }

}
