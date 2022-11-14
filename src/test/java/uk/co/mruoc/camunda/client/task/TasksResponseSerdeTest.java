package uk.co.mruoc.camunda.client.task;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.camunda.client.ObjectMapperFactory;

class TasksResponseSerdeTest {
    private static final ObjectMapper MAPPER = ObjectMapperFactory.build();

    private static final TasksResponse RESPONSE = TasksResponseMother.build();
    private static final String JSON = TasksResponseJsonMother.build();

    @Test
    void shouldSerialize() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(RESPONSE);

        assertThatJson(json).isEqualTo(JSON);
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        TasksResponse response = MAPPER.readValue(JSON, TasksResponse.class);

        assertThat(response).usingRecursiveComparison().isEqualTo(RESPONSE);
    }
}
