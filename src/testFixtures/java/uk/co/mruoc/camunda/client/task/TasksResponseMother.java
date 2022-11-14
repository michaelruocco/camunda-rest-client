package uk.co.mruoc.camunda.client.task;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TasksResponseMother {

    public static TasksResponse empty() {
        return new TasksResponse();
    }

    public static TasksResponse withTasks(Task... tasks) {
        return new TasksResponse(List.of(tasks));
    }

    public static TasksResponse build() {
        return new TasksResponse(TaskMother.build());
    }
}
