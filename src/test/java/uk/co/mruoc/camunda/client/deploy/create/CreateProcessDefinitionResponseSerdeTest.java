package uk.co.mruoc.camunda.client.deploy.create;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

class CreateProcessDefinitionResponseSerdeTest {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    private static final CreateDeploymentResponse RESPONSE = CreateDeploymentResponseMother.build();
    private static final String JSON = CreateDeploymentResponseJsonMother.build();

    @Test
    void shouldSerialize() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(RESPONSE);

        assertThatJson(json).isEqualTo(JSON);
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        CreateDeploymentResponse response = MAPPER.readValue(JSON, CreateDeploymentResponse.class);

        assertThat(response).usingRecursiveComparison().isEqualTo(RESPONSE);
    }
}
