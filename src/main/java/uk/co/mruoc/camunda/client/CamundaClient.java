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
import uk.co.mruoc.camunda.client.history.process.GetHistoricProcessInstancesRequest;
import uk.co.mruoc.camunda.client.history.process.HistoricProcessInstancesResponse;
import uk.co.mruoc.camunda.client.history.variable.GetHistoricVariableInstancesRequest;
import uk.co.mruoc.camunda.client.history.variable.HistoricVariableInstancesResponse;
import uk.co.mruoc.camunda.client.message.DeliverMessageRequest;
import uk.co.mruoc.camunda.client.process.get.GetProcessInstancesRequest;
import uk.co.mruoc.camunda.client.process.get.ProcessInstancesResponse;
import uk.co.mruoc.camunda.client.process.get.variable.GetVariableInstanceRequest;
import uk.co.mruoc.camunda.client.process.get.variable.VariableInstanceResponse;
import uk.co.mruoc.camunda.client.process.start.StartProcessRequest;
import uk.co.mruoc.camunda.client.process.start.StartProcessResponse;
import uk.co.mruoc.camunda.client.task.GetTaskByProcessInstanceBusinessKeyRequest;
import uk.co.mruoc.camunda.client.task.TasksResponse;
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

    public TasksResponse getTask(GetTaskByProcessInstanceBusinessKeyRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        return responseConverter.toTypeOrThrowError(response, TasksResponse.class);
    }

    public ProcessInstancesResponse getProcessInstances(GetProcessInstancesRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        return responseConverter.toTypeOrThrowError(response, ProcessInstancesResponse.class);
    }

    public VariableInstanceResponse getVariableInstance(GetVariableInstanceRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        return responseConverter.toTypeOrThrowError(response, VariableInstanceResponse.class);
    }

    public HistoricProcessInstancesResponse getHistoricProcessInstances(GetHistoricProcessInstancesRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        return responseConverter.toTypeOrThrowError(response, HistoricProcessInstancesResponse.class);
    }

    public HistoricVariableInstancesResponse getHistoricVariableInstances(GetHistoricVariableInstancesRequest request) {
        HttpRequest httpRequest = toHttpRequest(request);
        HttpResponse<String> response = executor.execute(httpRequest);
        return responseConverter.toTypeOrThrowError(response, HistoricVariableInstancesResponse.class);
    }

    private HttpRequest toHttpRequest(Object object) {
        return requestConverter.toHttpRequest(object).orElseThrow(() -> new RequestNotSupportedException(object));
    }
}
