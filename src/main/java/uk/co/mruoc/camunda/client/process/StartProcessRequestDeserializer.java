package uk.co.mruoc.camunda.client.process;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import uk.co.mruoc.camunda.client.variable.Variables;
import uk.co.mruoc.json.jackson.JsonNodeConverter;

public class StartProcessRequestDeserializer extends StdDeserializer<StartProcessRequest> {

    public StartProcessRequestDeserializer() {
        super(StartProcessRequest.class);
    }

    @Override
    public StartProcessRequest deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        return StartProcessRequest.builder()
                .variables(JsonNodeConverter.toObject(node.get("variables"), parser, Variables.class))
                .businessKey(node.get("businessKey").asText())
                .build();
    }
}
