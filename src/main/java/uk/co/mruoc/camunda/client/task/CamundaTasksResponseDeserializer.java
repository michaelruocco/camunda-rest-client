package uk.co.mruoc.camunda.client.task;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.util.Collection;
import uk.co.mruoc.json.jackson.JsonNodeConverter;
import uk.co.mruoc.json.jackson.JsonParserConverter;

public class CamundaTasksResponseDeserializer extends StdDeserializer<CamundaTasksResponse> {

    private static final TypeReference<Collection<CamundaTask>> TASK_COLLECTION = new TypeReference<>() {
                // intentionally blank
            };

    protected CamundaTasksResponseDeserializer() {
        super(CamundaTasksResponse.class);
    }

    @Override
    public CamundaTasksResponse deserialize(JsonParser parser, DeserializationContext context) {
        JsonNode node = JsonParserConverter.toNode(parser);
        return new CamundaTasksResponse(JsonNodeConverter.toCollection(node, parser, TASK_COLLECTION));
    }
}
