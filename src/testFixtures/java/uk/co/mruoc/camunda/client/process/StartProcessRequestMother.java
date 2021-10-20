package uk.co.mruoc.camunda.client.process;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.process.variable.LongVariable;
import uk.co.mruoc.camunda.client.process.variable.StringVariable;
import uk.co.mruoc.camunda.client.process.variable.Variable;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartProcessRequestMother {

    public static StartProcessRequest build() {
        return withDefinitionProcessKey("external-script-demo-process");
    }

    public static StartProcessRequest withDefinitionProcessKey(String processKey) {
        return StartProcessRequest.builder()
                .processDefinitionKey(processKey)
                .variables(variables())
                .build();
    }

    private static Collection<Variable> variables() {
        return Arrays.asList(
                new StringVariable("inputString", "hi joe bloggs"),
                new LongVariable("inputLong", 0)
        );
    }

}
