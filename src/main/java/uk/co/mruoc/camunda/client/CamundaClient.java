package uk.co.mruoc.camunda.client;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentRequest;
import uk.co.mruoc.camunda.client.deploy.create.CreateDeploymentResponse;
import uk.co.mruoc.camunda.client.deploy.delete.DeleteDeploymentRequest;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequest;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsResponse;
import uk.co.mruoc.camunda.client.message.DeliverMessageRequest;
import uk.co.mruoc.camunda.client.process.StartProcessRequest;
import uk.co.mruoc.camunda.client.process.StartProcessResponse;
import uk.co.mruoc.json.JsonConverter;
import uk.co.mruoc.json.jackson.JacksonJsonConverter;

@Builder
@RequiredArgsConstructor
public class CamundaClient {

    private final String baseUri;
    private final RequestExecutor executor;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    public CamundaClient(String baseUri) {
        this(baseUri, new JacksonJsonConverter(ObjectMapperFactory.build()));
    }

    public CamundaClient(String baseUri, JsonConverter jsonConverter) {
        this(baseUri, new RequestExecutor(), new CompositeRequestConverter(baseUri, jsonConverter), jsonConverter);
    }

    public CamundaClient(
            String baseUri,
            RequestExecutor requestExecutor,
            RequestConverter requestConverter,
            JsonConverter jsonConverter) {
        this(baseUri, requestExecutor, requestConverter, new ResponseConverter(jsonConverter));
    }

    public CreateDeploymentResponse createDeployment(CreateDeploymentRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        return responseConverter.toTypeOrThrowError(response, CreateDeploymentResponse.class);
    }

    public GetDeploymentsResponse getDeployments(GetDeploymentsRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        return responseConverter.toTypeOrThrowError(response, GetDeploymentsResponse.class);
    }

    public void deleteDeployment(DeleteDeploymentRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        responseConverter.throwErrorIfRequired(response);
    }

    public StartProcessResponse startProcess(StartProcessRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        return responseConverter.toTypeOrThrowError(response, StartProcessResponse.class);
    }

    public void deliverMessage(DeliverMessageRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        responseConverter.throwErrorIfRequired(response);
    }

    private HttpRequest toHttpRequest(Object object) {
        return requestConverter.toHttpRequest(object).orElseThrow(() -> new RequestNotSupportedException(object));
    }
}
