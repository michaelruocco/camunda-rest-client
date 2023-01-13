package uk.co.mruoc.camunda.client.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.variable.JsonVariable;
import uk.co.mruoc.camunda.client.variable.Variables;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliverMessageRequestMother {

    public static DeliverMessageRequest buildDelierMessageRequest(String businessKey) {
        return DeliverMessageRequest.builder()
                .messageName("receive-message-for-user-task")
                .businessKey(businessKey)
                .processVariables(new Variables(new JsonVariable("messageInput", "{\"value\":\"hello\"}")))
                .build();
    }
}
