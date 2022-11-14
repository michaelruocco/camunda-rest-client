package uk.co.mruoc.camunda.client.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GetTaskByProcessInstanceBusinessKeyRequest {
    private final String businessKey;
}
