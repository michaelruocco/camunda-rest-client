package uk.co.mruoc.camunda.client;

import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.CreateDeploymentRequestConverter;
import uk.co.mruoc.camunda.client.process.StartProcessRequestConverter;
import uk.co.mruoc.json.JsonConverter;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class CompositeRequestConverter {

    private final Collection<RequestConverter> requestConverters;

    public CompositeRequestConverter(String baseUri, JsonConverter jsonConverter) {
        this(buildConverters(baseUri, jsonConverter));
    }

    private static Collection<RequestConverter> buildConverters(String baseUri, JsonConverter jsonConverter) {
        return Arrays.asList(
                new StartProcessRequestConverter(baseUri, jsonConverter),
                new CreateDeploymentRequestConverter(baseUri)
        );
    }

    public HttpRequest toHttpRequest(Object object) {
        return requestConverters.stream()
                .map(converter -> converter.toHttpRequest(object))
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new RequestNotSupportedException(object));
    }

}
