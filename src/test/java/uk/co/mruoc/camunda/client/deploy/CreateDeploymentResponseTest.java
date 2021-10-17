package uk.co.mruoc.camunda.client.deploy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateDeploymentResponseTest {

    @Test
    void shouldReturnFirstDeployedProcessDefinitionKeyIfDeployedProcessDefinitionsPresent() {
        ProcessDefinition process1 = givenProcessDefinitionWithKey("key-1");
        ProcessDefinition process2 = givenProcessDefinitionWithKey("key-2");
        CreateDeploymentResponse response = CreateDeploymentResponse.builder()
                .deployedProcessDefinitions(Arrays.asList(process1, process2))
                .build();

        String firstKey = response.getFirstDeployedProcessDefinitionKey();

        assertThat(firstKey).isEqualTo(process1.getKey());
    }

    @Test
    void shouldThrowExceptionIfNoDeployedProcessDefinitionsPresent() {
        CreateDeploymentResponse response = CreateDeploymentResponse.builder()
                .deployedProcessDefinitions(Collections.emptyList())
                .build();

        Throwable error = catchThrowable(response::getFirstDeployedProcessDefinitionKey);

        assertThat(error).isInstanceOf(NoProcessDefinitionsDeployedException.class);
    }

    private ProcessDefinition givenProcessDefinitionWithKey(String key) {
        ProcessDefinition processDefinition = mock(ProcessDefinition.class);
        when(processDefinition.getKey()).thenReturn(key);
        return processDefinition;
    }

}
