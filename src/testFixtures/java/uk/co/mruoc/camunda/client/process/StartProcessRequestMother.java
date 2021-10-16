package uk.co.mruoc.camunda.client.process;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.process.variable.StringVariable;

import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartProcessRequestMother {

    public static StartProcessRequest build() {
        return withDefinitionProcessKey("external-script-demo-process");
    }

    public static StartProcessRequest withDefinitionProcessKey(String processKey) {
        return StartProcessRequest.builder()
                .processDefinitionKey(processKey)
                .variables(Collections.singleton(new StringVariable("input", "hi joe bloggs")))
                .build();
    }

}
