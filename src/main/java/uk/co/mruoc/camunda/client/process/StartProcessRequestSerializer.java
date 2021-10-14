package uk.co.mruoc.camunda.client.process;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.camunda.client.process.variable.Variable;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StartProcessRequestSerializer extends StdSerializer<StartProcessRequest> {

    protected StartProcessRequestSerializer() {
        super(StartProcessRequest.class);
    }

    @Override
    public void serialize(StartProcessRequest request, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeStartObject();
        writeVariables(json, request.getVariables());
        writeBusinessKey(json, request.getBusinessKey());
        json.writeEndObject();
    }

    private static void writeVariables(JsonGenerator json, Collection<Variable> variables) throws IOException {
        if (variables.isEmpty()) {
            return;
        }
        json.writeObjectField("variables", toMap(variables));
    }

    private static void writeBusinessKey(JsonGenerator json, String businessKey) throws IOException {
        if (StringUtils.isEmpty(businessKey)) {
            return;
        }
        json.writeStringField("businessKey", businessKey);
    }

    private static Map<String, Variable> toMap(Collection<Variable> variables) {
        return variables.stream().collect(Collectors.toMap(Variable::getName, Function.identity()));
    }

}
