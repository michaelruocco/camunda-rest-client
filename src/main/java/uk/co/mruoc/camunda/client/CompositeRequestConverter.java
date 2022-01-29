package uk.co.mruoc.camunda.client;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestConverter;
import uk.co.mruoc.camunda.client.deploy.delete.DeleteDeploymentRequestConverter;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequestConverter;
import uk.co.mruoc.camunda.client.message.DeliverMessageRequestConverter;
import uk.co.mruoc.camunda.client.process.StartProcessRequestConverter;
import uk.co.mruoc.json.JsonConverter;

@RequiredArgsConstructor
public class CompositeRequestConverter {

    private final Collection<RequestConverter> requestConverters;

    public CompositeRequestConverter(String baseUri, JsonConverter jsonConverter) {
        this(buildConverters(baseUri, jsonConverter));
    }

    private static Collection<RequestConverter> buildConverters(String baseUri, JsonConverter jsonConverter) {
        return Arrays.asList(
                new StartProcessRequestConverter(baseUri, jsonConverter),
                new CreateDeploymentRequestConverter(baseUri),
                new GetDeploymentsRequestConverter(baseUri),
                new DeleteDeploymentRequestConverter(baseUri),
                new DeliverMessageRequestConverter(baseUri, jsonConverter));
    }

    public HttpRequest toHttpRequest(Object object) {
        return requestConverters.stream()
                .map(converter -> converter.toHttpRequest(object))
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new RequestNotSupportedException(object));
    }
}
