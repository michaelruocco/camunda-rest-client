package uk.co.mruoc.camunda.client.history.variable;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

class HistoricVariableInstancesResponseSerdeTest {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    private static final HistoricVariableInstancesResponse RESPONSE = HistoricVariableInstanceResponseMother.build();
    private static final String JSON = HistoricVariableInstanceResponseJsonMother.build();

    @Test
    void shouldSerialize() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(RESPONSE);

        assertThatJson(json).isEqualTo(JSON);
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        HistoricVariableInstancesResponse response = MAPPER.readValue(JSON, HistoricVariableInstancesResponse.class);

        assertThat(response).usingRecursiveComparison().isEqualTo(RESPONSE);
    }
}
