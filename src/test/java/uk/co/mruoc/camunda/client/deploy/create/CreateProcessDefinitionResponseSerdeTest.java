package uk.co.mruoc.camunda.client.deploy.create;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

class CreateProcessDefinitionResponseSerdeTest {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    private static final CreateDeploymentResponse RESPONSE = CreateDeploymentResponseMother.build();
    private static final String JSON = CreateDeploymentResponseJsonMother.build();

    @Test
    void shouldSerialize() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(RESPONSE);

        assertThatJson(json).whenIgnoringPaths("deploymentTime").isEqualTo(JSON);
        assertThatJson(json).inPath("deploymentTime").isEqualTo("2021-10-16T10:59:37.387Z");
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        CreateDeploymentResponse response = MAPPER.readValue(JSON, CreateDeploymentResponse.class);

        assertThat(response).usingRecursiveComparison().isEqualTo(RESPONSE);
    }

}
