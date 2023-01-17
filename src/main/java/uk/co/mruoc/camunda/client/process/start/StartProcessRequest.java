package uk.co.mruoc.camunda.client.process.start;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.variable.Variables;

@Builder
@RequiredArgsConstructor
@JsonSerialize(using = StartProcessRequestSerializer.class)
@JsonDeserialize(using = StartProcessRequestDeserializer.class)
@Data
public class StartProcessRequest {

    private final String processDefinitionKey;
    private final Variables variables;
    private final String businessKey;
}
