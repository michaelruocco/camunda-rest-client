package uk.co.mruoc.camunda.client.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Data
@JsonDeserialize(using = TasksResponseDeserializer.class)
@JsonSerialize(using = TasksResponseSerializer.class)
public class TasksResponse {

    private final Collection<Task> tasks;

    public TasksResponse(Task... tasks) {
        this(List.of(tasks));
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
