package uk.co.mruoc.camunda.client.deploy.create;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import uk.co.mruoc.camunda.client.Link;
import uk.co.mruoc.json.jackson.JsonNodeConverter;
import uk.co.mruoc.json.jackson.JsonParserConverter;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static uk.co.mruoc.json.jackson.JsonNodeConverter.toCollection;

public class CreateDeploymentResponseDeserializer extends StdDeserializer<CreateDeploymentResponse> {

    private static final TypeReference<Collection<Link>> LINK_COLLECTION = new TypeReference<>() {
        // intentionally blank
    };

    private static final TypeReference<Map<String, ProcessDefinition>> PROCESS_DEFINITION_MAP = new TypeReference<>() {
        // intentionally blank
    };

    protected CreateDeploymentResponseDeserializer() {
        super(CreateDeploymentResponse.class);
    }

    @Override
    public CreateDeploymentResponse deserialize(JsonParser parser, DeserializationContext context) {
        JsonNode node = JsonParserConverter.toNode(parser);
        return CreateDeploymentResponse.builder()
                .links(toCollection(node.get("links"), parser, LINK_COLLECTION))
                .id(UUID.fromString(node.get("id").asText()))
                .name(node.get("name").asText())
                .source(node.get("source").asText())
                .deploymentTime(JsonNodeConverter.toObject(node.get("deploymentTime"), parser, OffsetDateTime.class))
                .tenantId(JsonNodeConverter.toObject(node.get("tenantId"), parser, String.class))
                .deployedProcessDefinitions(toCollection(node.get("deployedProcessDefinitions"), parser, PROCESS_DEFINITION_MAP).values())
                .build();
    }

}
