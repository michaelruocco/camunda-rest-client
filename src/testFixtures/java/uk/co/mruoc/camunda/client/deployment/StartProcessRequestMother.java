package uk.co.mruoc.camunda.client.deployment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.process.StartProcessRequest;
import uk.co.mruoc.camunda.client.process.variable.StringVariable;

import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartProcessRequestMother {

    public static StartProcessRequest build() {
        return withProcessKey("external-script-demo-process");
    }

    public static StartProcessRequest withProcessKey(String processKey) {
        return StartProcessRequest.builder()
                .processKey(processKey)
                .variables(Collections.singleton(new StringVariable("input", "hi joe bloggs")))
                .build();
    }

}
