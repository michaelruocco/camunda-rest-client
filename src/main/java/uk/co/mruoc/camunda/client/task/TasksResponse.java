package uk.co.mruoc.camunda.client.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonDeserialize(using = TasksResponseDeserializer.class)
@JsonSerialize(using = TasksResponseSerializer.class)
public class TasksResponse {

    private final Collection<Task> tasks;

    public TasksResponse(Task... tasks) {
        this(List.of(tasks));
    }

    public TasksResponse(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Collection<UUID> getTaskIds() {
        return tasks.stream().map(Task::getId).collect(Collectors.toList());
    }

    public Optional<Task> getFirstTask() {
        return tasks.stream().findFirst();
    }

    public Task forceGetFirstTask() {
        return getFirstTask().orElseThrow(NoTasksPresentException::new);
    }
}
