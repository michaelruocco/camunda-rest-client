package uk.co.mruoc.camunda.client.process;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import uk.co.mruoc.camunda.client.variable.Variable;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StartProcessRequestSerializer extends StdSerializer<StartProcessRequest> {

    protected StartProcessRequestSerializer() {
        super(StartProcessRequest.class);
    }

    @Override
    public void serialize(StartProcessRequest request, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeStartObject();
        Collection<Variable> variables = Optional.ofNullable(request.getVariables()).orElse(Collections.emptyList());
        provider.defaultSerializeField("variables", toMap(variables), json);
        provider.defaultSerializeField("businessKey", request.getBusinessKey(), json);
        json.writeEndObject();
    }

    private static Map<String, Variable> toMap(Collection<Variable> variables) {
        return variables.stream().collect(Collectors.toMap(Variable::getName, Function.identity()));
    }

}
