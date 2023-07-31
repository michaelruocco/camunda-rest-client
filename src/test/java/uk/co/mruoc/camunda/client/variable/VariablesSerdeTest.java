package uk.co.mruoc.camunda.client.variable;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.camunda.client.ObjectMapperFactory;

class VariablesSerdeTest {
    private static final ObjectMapper MAPPER = ObjectMapperFactory.build();

    @Test
    void shouldSerializeValidVariables() throws JsonProcessingException {
        Variables variables = VariablesMother.build();

        String json = MAPPER.writeValueAsString(variables);

        assertThatJson(json).isEqualTo(VariablesJsonMother.build());
    }

    @Test
    void shouldDeserializeValidVariables() throws JsonProcessingException {
        String json = VariablesJsonMother.build();

        Variables variables = MAPPER.readValue(json, Variables.class);

        assertThat(variables).usingRecursiveComparison().isEqualTo(VariablesMother.build());
    }

    @Test
    void shouldThrowExceptionIfAttemptToDeserializeUnsupportedVariableType() {
        String json = VariablesJsonMother.unsupported();

        Throwable error = catchThrowable(() -> MAPPER.readValue(json, Variables.class));

        assertThat(error).isInstanceOf(VariableTypeUnsupportedException.class).hasMessage("unsupportedType");
    }
}
