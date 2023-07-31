package uk.co.mruoc.camunda.client.process.get.variable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.history.variable.ValueInfo;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariableInstanceResponse {

    private final String type;
    private final Object value;
    private final ValueInfo valueInfo;
}
