package uk.co.mruoc.camunda.client.variable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class VariablesDeserializer extends StdDeserializer<Variables> {

    protected VariablesDeserializer() {
        super(Variables.class);
    }

    @Override
    public Variables deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        Collection<Variable> variables = toCollection(node.fields());
        return new Variables(variables);
    }

    private static Collection<Variable> toCollection(Iterator<Map.Entry<String, JsonNode>> variableFields) {
        Collection<Variable> variables = new ArrayList<>();
        variableFields.forEachRemaining(field -> variables.add(toVariable(field)));
        return Collections.unmodifiableCollection(variables);
    }

    private static Variable toVariable(Map.Entry<String, JsonNode> field) {
        return toVariable(
                field.getValue().get("type"), field.getKey(), field.getValue().get("value"));
    }

    private static Variable toVariable(JsonNode typeNode, String name, JsonNode value) {
        String type = typeNode.asText();
        switch (type.toLowerCase()) {
            case "json":
                return new JsonVariable(name, value.asText());
            case "string":
                return new StringVariable(name, value.asText());
            case "long":
                return new LongVariable(name, value.asLong());
            case "boolean":
                return new BooleanVariable(name, value.asBoolean());
            default:
                throw new VariableTypeUnsupportedException(type);
        }
    }
}
