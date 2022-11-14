package uk.co.mruoc.camunda.client.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
@JsonDeserialize(using = CamundaTasksResponseDeserializer.class)
public class CamundaTasksResponse {

    private final Collection<CamundaTask> tasks;

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Collection<UUID> getTaskIds() {
        return tasks.stream().map(CamundaTask::getId).collect(Collectors.toList());
    }

    public Optional<CamundaTask> getFirstTask() {
        return tasks.stream().findFirst();
    }

    public CamundaTask forceGetFirstTask() {
        return getFirstTask().orElseThrow(NoCamundaTasksFoundException::new);
    }
}
