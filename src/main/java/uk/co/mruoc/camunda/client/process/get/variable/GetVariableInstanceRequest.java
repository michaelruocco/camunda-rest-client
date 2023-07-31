package uk.co.mruoc.camunda.client.process.get.variable;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
@Data
public class GetVariableInstanceRequest {

    private final UUID processInstanceId;
    private final String variableName;
    private final boolean deserializeValue;
}
