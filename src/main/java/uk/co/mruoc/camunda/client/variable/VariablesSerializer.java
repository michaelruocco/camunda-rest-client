package uk.co.mruoc.camunda.client.variable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class VariablesSerializer extends StdSerializer<Variables> {

    public VariablesSerializer() {
        super(Variables.class);
    }

    @Override
    public void serialize(Variables variables, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeStartObject();
        for (Variable variable : variables.getValues()) {
            writeVariable(variable, json, provider);
        }
        json.writeEndObject();
    }

    private void writeVariable(Variable variable, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeFieldName(variable.getName());
        json.writeStartObject();
        provider.defaultSerializeField("value", variable.getValue(), json);
        json.writeStringField("type", variable.getType());
        json.writeEndObject();
    }

}
