package uk.co.mruoc.camunda.client.task;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.OffsetDateTimeParser;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskMother {

    public static Task build() {
        return builder().build();
    }

    public static Task withId(UUID id) {
        return builder().id(id).build();
    }

    public static Task.TaskBuilder builder() {
        return Task.builder()
                .id(UUID.fromString("c4ebe304-6409-11ed-8669-52a355e7889c"))
                .name("task task name")
                .created(OffsetDateTimeParser.parse("2022-11-14T10:59:37.387+0000"))
                .due(OffsetDateTimeParser.parse("2022-11-15T10:59:37.387+0000"))
                .executionId(UUID.fromString("c4e8fcce-6409-11ed-8669-52a355e7889c"))
                .priority(150)
                .processDefinitionId("test-process:1:ea6e6b99-0d16-11ed-845a-0ebf23a48bbc")
                .processInstanceId(UUID.fromString("d0b94249-22cf-11ed-91cc-3a295e9d2072"))
                .taskDefinitionKey("test-task")
                .suspended(false)
                .formKey("test-task");
    }
}
