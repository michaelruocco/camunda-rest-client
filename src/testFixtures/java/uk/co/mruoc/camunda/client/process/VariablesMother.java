package uk.co.mruoc.camunda.client.process;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.variable.JsonVariable;
import uk.co.mruoc.camunda.client.variable.LongVariable;
import uk.co.mruoc.camunda.client.variable.StringVariable;
import uk.co.mruoc.camunda.client.variable.Variables;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VariablesMother {

    public static Variables build() {
        return new Variables(
                new StringVariable("inputString", "hi joe bloggs"),
                new LongVariable("inputLong", 0),
                new JsonVariable("inputJson", "{\"data\": {\"field\": \"value\"}}"));
    }
}
