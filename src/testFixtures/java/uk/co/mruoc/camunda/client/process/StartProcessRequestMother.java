package uk.co.mruoc.camunda.client.process;

import java.util.Arrays;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.variable.JsonVariable;
import uk.co.mruoc.camunda.client.variable.LongVariable;
import uk.co.mruoc.camunda.client.variable.StringVariable;
import uk.co.mruoc.camunda.client.variable.Variable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartProcessRequestMother {

    public static StartProcessRequest build() {
        return withDefinitionProcessKey("external-script-demo-process");
    }

    public static StartProcessRequest withDefinitionProcessKey(String processKey) {
        return StartProcessRequest.builder()
                .processDefinitionKey(processKey)
                .variables(variables())
                .businessKey("default-business-key")
                .build();
    }

    private static Collection<Variable> variables() {
        return Arrays.asList(
                new StringVariable("inputString", "hi joe bloggs"),
                new LongVariable("inputLong", 0),
                new JsonVariable("inputJson", "{\"data\": {\"field\": \"value\"}}"));
    }
}
