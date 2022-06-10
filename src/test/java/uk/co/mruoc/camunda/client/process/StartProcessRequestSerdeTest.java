package uk.co.mruoc.camunda.client.process;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.camunda.client.ObjectMapperFactory;

class StartProcessRequestSerdeTest {
    private static final ObjectMapper MAPPER = ObjectMapperFactory.build();

    private static final StartProcessRequest REQUEST = StartProcessRequestMother.build();
    private static final String JSON = StartProcessRequestJsonMother.build();

    @Test
    void shouldSerialize() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(REQUEST);

        assertThatJson(json).isEqualTo(JSON);
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        StartProcessRequest request = MAPPER.readValue(JSON, StartProcessRequest.class);
        assertThat(request)
                .usingRecursiveComparison()
                .ignoringFields("processDefinitionKey")
                .isEqualTo(REQUEST);
    }
}
