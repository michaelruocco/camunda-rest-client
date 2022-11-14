package uk.co.mruoc.camunda.client.task;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TasksResponseMother {

    public static TasksResponse empty() {
        return new TasksResponse();
    }

    public static TasksResponse withTasks(Task... tasks) {
        return new TasksResponse(tasks);
    }

    public static TasksResponse build() {
        return new TasksResponse(TaskMother.build());
    }
}
