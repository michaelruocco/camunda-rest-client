package uk.co.mruoc.camunda.client.history.process;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

class HistoricProcessInstancesResponseSerdeTest {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    private static final HistoricProcessInstancesResponse RESPONSE = HistoricProcessInstancesResponseMother.build();
    private static final String JSON = HistoricProcessInstancesResponseJsonMother.build();

    @Test
    void shouldSerialize() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(RESPONSE);

        assertThatJson(json).isEqualTo(JSON);
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        HistoricProcessInstancesResponse response = MAPPER.readValue(JSON, HistoricProcessInstancesResponse.class);

        assertThat(response).usingRecursiveComparison().isEqualTo(RESPONSE);
    }
}
