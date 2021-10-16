package uk.co.mruoc.camunda.client.process;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.process.variable.Variable;

import java.util.Collection;

@Builder
@RequiredArgsConstructor
@JsonSerialize(using = StartProcessRequestSerializer.class)
@Data
public class StartProcessRequest {

    private final String processDefinitionKey;
    private final Collection<Variable> variables;
    private final String businessKey;

}
