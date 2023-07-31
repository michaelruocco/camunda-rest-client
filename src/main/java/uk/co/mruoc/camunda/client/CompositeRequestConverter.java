package uk.co.mruoc.camunda.client;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequestConverter;
import uk.co.mruoc.camunda.client.deploy.delete.DeleteDeploymentRequestConverter;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequestConverter;
import uk.co.mruoc.camunda.client.header.HeaderPopulator;
import uk.co.mruoc.camunda.client.header.NoopHeaderPopulator;
import uk.co.mruoc.camunda.client.history.process.GetHistoricProcessInstancesRequestConverter;
import uk.co.mruoc.camunda.client.history.variable.GetHistoricVariableInstancesRequestConverter;
import uk.co.mruoc.camunda.client.message.DeliverMessageRequestConverter;
import uk.co.mruoc.camunda.client.process.get.GetProcessInstancesByBusinessKeyRequestConverter;
import uk.co.mruoc.camunda.client.process.get.variable.GetVariableInstanceRequestConverter;
import uk.co.mruoc.camunda.client.process.start.StartProcessRequestConverter;
import uk.co.mruoc.camunda.client.task.GetTaskByProcessInstanceBusinessKeyRequestConverter;
import uk.co.mruoc.json.JsonConverter;

@RequiredArgsConstructor
public class CompositeRequestConverter implements RequestConverter {

    private final Collection<RequestConverter> requestConverters;

    public CompositeRequestConverter(String baseUri, JsonConverter jsonConverter) {
        this(baseUri, jsonConverter, new NoopHeaderPopulator());
    }

    public CompositeRequestConverter(String baseUri, JsonConverter jsonConverter, HeaderPopulator headerPopulator) {
        this(buildConverters(baseUri, jsonConverter, headerPopulator));
    }

    private static Collection<RequestConverter> buildConverters(
            String baseUri, JsonConverter jsonConverter, HeaderPopulator headerPopulator) {
        return Arrays.asList(
                new StartProcessRequestConverter(baseUri, jsonConverter, headerPopulator),
                new CreateDeploymentRequestConverter(baseUri, headerPopulator),
                new GetDeploymentsRequestConverter(baseUri, headerPopulator),
                new DeleteDeploymentRequestConverter(baseUri, headerPopulator),
                new DeliverMessageRequestConverter(baseUri, jsonConverter, headerPopulator),
                new GetTaskByProcessInstanceBusinessKeyRequestConverter(baseUri, headerPopulator),
                new GetProcessInstancesByBusinessKeyRequestConverter(baseUri, jsonConverter, headerPopulator),
                new GetHistoricProcessInstancesRequestConverter(baseUri, jsonConverter, headerPopulator),
                new GetHistoricVariableInstancesRequestConverter(baseUri, jsonConverter, headerPopulator),
                new GetVariableInstanceRequestConverter(baseUri, jsonConverter, headerPopulator));
    }

    @Override
    public Optional<HttpRequest> apply(Object object) {
        return toHttpRequest(object);
    }

    @Override
    public Optional<HttpRequest> toHttpRequest(Object object) {
        return requestConverters.stream()
                .map(converter -> converter.toHttpRequest(object))
                .flatMap(Optional::stream)
                .findFirst();
    }
}
