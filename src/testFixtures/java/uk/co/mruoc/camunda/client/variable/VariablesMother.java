package uk.co.mruoc.camunda.client.variable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VariablesMother {

    public static Variables build() {
        return new Variables(
                new StringVariable("inputString", "hi joe bloggs"),
                new LongVariable("inputLong", 0),
                new JsonVariable("inputJson", "{\"data\": {\"field\": \"value\"}}"),
                new BooleanVariable("inputBoolean", true));
    }
}
