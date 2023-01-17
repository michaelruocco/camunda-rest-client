package uk.co.mruoc.camunda.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import uk.co.mruoc.camunda.client.history.process.GetHistoricProcessInstancesRequest;
import uk.co.mruoc.camunda.client.history.process.HistoricProcessInstance;
import uk.co.mruoc.camunda.client.history.process.HistoricProcessInstancesResponse;
import uk.co.mruoc.camunda.client.history.variable.GetHistoricVariableInstancesRequest;
import uk.co.mruoc.camunda.client.history.variable.HistoricVariableInstance;
import uk.co.mruoc.camunda.client.history.variable.HistoricVariableInstancesResponse;

@Testcontainers
class CamundaClientHistoryIntegrationTest {

    @Container
    public static final FakeLocalCamunda CAMUNDA = new FakeLocalCamunda();

    private static CamundaClient client;

    @BeforeAll
    static void setUp() {
        client = new CamundaClient(CAMUNDA.getUri());
    }

    @Test
    void shouldGetHistoricProcessInstances() {
        GetHistoricProcessInstancesRequest request = GetHistoricProcessInstancesRequest.builder()
                .processInstanceBusinessKeyIn(Collections.singleton("key-1"))
                .build();

        HistoricProcessInstancesResponse response = client.getHistoricProcessInstances(request);

        assertThat(response)
                .hasSize(1)
                .map(HistoricProcessInstance::getBusinessKey)
                .containsExactly("key-1");
    }

    @Test
    void shouldGetHistoricVariableInstances() {
        GetHistoricVariableInstancesRequest request = GetHistoricVariableInstancesRequest.builder()
                .processInstanceIdIn(Collections.singleton("process-1"))
                .variableNameIn(Collections.singleton("variable-name-1"))
                .build();

        HistoricVariableInstancesResponse response = client.getHistoricVariableInstances(request);

        assertThat(response).hasSize(1).map(HistoricVariableInstance::getValue).containsExactly("variable-value-1");
    }
}
