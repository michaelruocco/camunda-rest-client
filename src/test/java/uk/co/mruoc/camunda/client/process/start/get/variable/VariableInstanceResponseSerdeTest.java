package uk.co.mruoc.camunda.client.process.start.get.variable;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.camunda.client.ObjectMapperFactory;
import uk.co.mruoc.camunda.client.process.get.variable.VariableInstanceResponse;
import uk.co.mruoc.camunda.client.process.get.variable.VariableInstanceResponseJsonMother;
import uk.co.mruoc.camunda.client.process.get.variable.VariableInstanceResponseMother;

class VariableInstanceResponseSerdeTest {
    private static final ObjectMapper MAPPER = ObjectMapperFactory.build();

    private static final VariableInstanceResponse RESPONSE = VariableInstanceResponseMother.buildString();
    private static final String JSON = VariableInstanceResponseJsonMother.build();

    @Test
    void shouldSerialize() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(RESPONSE);

        assertThatJson(json).isEqualTo(JSON);
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        VariableInstanceResponse response = MAPPER.readValue(JSON, VariableInstanceResponse.class);

        assertThat(response).usingRecursiveComparison().isEqualTo(RESPONSE);
    }
}
