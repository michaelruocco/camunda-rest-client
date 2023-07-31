package uk.co.mruoc.camunda.client.process.get.variable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.history.variable.ValueInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VariableInstanceResponseMother {

    public static VariableInstanceResponse buildString() {
        return builder().type("String").value("some value").build();
    }

    private static VariableInstanceResponse.VariableInstanceResponseBuilder builder() {
        return VariableInstanceResponse.builder().valueInfo(new ValueInfo());
    }
}
