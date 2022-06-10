package uk.co.mruoc.camunda.client.process;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import uk.co.mruoc.camunda.client.variable.JsonVariable;
import uk.co.mruoc.camunda.client.variable.LongVariable;
import uk.co.mruoc.camunda.client.variable.StringVariable;
import uk.co.mruoc.camunda.client.variable.Variable;

public class StartProcessRequestDeserializer extends StdDeserializer<StartProcessRequest> {

    protected StartProcessRequestDeserializer() {
        super(StartProcessRequest.class);
    }

    @Override
    public StartProcessRequest deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JacksonException {

        JsonNode node = parser.getCodec().readTree(parser);
        return StartProcessRequest.builder()
                .variables(toCollection(node.get("variables").fields()))
                .businessKey(node.get("businessKey").asText())
                .build();
    }

    private static Collection<Variable> toCollection(Iterator<Entry<String, JsonNode>> jsonVariables) {
        Collection<Variable> variables = new ArrayList<>();
        jsonVariables.forEachRemaining(var -> variables.add(toVariable(
                var.getKey(),
                var.getValue().get("type").asText(),
                var.getValue().get("value"))));

        return variables;
    }

    private static Variable toVariable(String name, String type, JsonNode value) {
        switch (type) {
            case "json":
                return new JsonVariable(name, value.asText());
            case "string":
                return new StringVariable(name, value.asText());
            case "long":
                return new LongVariable(name, value.asLong());
            default:
                return null;
        }
    }
}
