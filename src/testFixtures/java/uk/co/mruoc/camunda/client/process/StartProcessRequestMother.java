package uk.co.mruoc.camunda.client.process;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.process.start.StartProcessRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartProcessRequestMother {

    public static StartProcessRequest build() {
        return withDefinitionProcessKey("external-script-demo-process");
    }

    public static StartProcessRequest withDefinitionProcessKey(String processKey) {
        return StartProcessRequest.builder()
                .processDefinitionKey(processKey)
                .variables(VariablesMother.build())
                .businessKey("default-business-key")
                .build();
    }
}
