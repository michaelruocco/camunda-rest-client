package uk.co.mruoc.camunda.client.process.start;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import uk.co.mruoc.camunda.client.variable.Variable;
import uk.co.mruoc.camunda.client.variable.Variables;

public class StartProcessRequestSerializer extends StdSerializer<StartProcessRequest> {

    protected StartProcessRequestSerializer() {
        super(StartProcessRequest.class);
    }

    @Override
    public void serialize(StartProcessRequest request, JsonGenerator json, SerializerProvider provider)
            throws IOException {
        json.writeStartObject();
        Variables variables = Optional.ofNullable(request.getVariables()).orElse(new Variables());
        provider.defaultSerializeField("variables", toMap(variables), json);
        provider.defaultSerializeField("businessKey", request.getBusinessKey(), json);
        json.writeEndObject();
    }

    private static Map<String, Variable> toMap(Variables variables) {
        return variables.stream().collect(Collectors.toMap(Variable::getName, Function.identity()));
    }
}
