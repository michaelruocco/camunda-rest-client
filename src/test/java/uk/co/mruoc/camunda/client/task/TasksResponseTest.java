package uk.co.mruoc.camunda.client.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.camunda.client.ObjectMapperFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class TasksResponseTest {
    private static final ObjectMapper MAPPER = ObjectMapperFactory.build();

    private static final TasksResponse RESPONSE = TasksResponseMother.build();
    private static final String JSON = TasksResponseJsonMother.build();

    @Test
    void shouldReturnEmptyFirstTaskIfNoTasksPresent() {
        TasksResponse response = TasksResponseMother.empty();

        Optional<Task> task = response.getFirstTask();

        assertThat(task).isEmpty();
    }

    @Test
    void shouldThrowExceptionIfNoTasksPresent() {
        TasksResponse response = TasksResponseMother.empty();

        Throwable error = catchThrowable(response::forceGetFirstTask);

        assertThat(error).isInstanceOf(NoTasksPresentException.class);
    }

    @Test
    void shouldReturnZeroNumberOfTasksIfEmpty() {
        TasksResponse response = TasksResponseMother.empty();

        int numberOfTasks = response.getNumberOfTasks();

        assertThat(numberOfTasks).isZero();
    }

    @Test
    void shouldReturnNumberOfTasksIfTasksPresent() {
        TasksResponse response = TasksResponseMother.build();

        int numberOfTasks = response.getNumberOfTasks();

        assertThat(numberOfTasks).isOne();
    }

    @Test
    void shouldReturnTaskIds() {
        Task task1 = TaskMother.withId(UUID.fromString("c8ec02aa-e8dc-4f97-ad09-35a80ea2491e"));
        Task task2 = TaskMother.withId(UUID.fromString("3c7bafbb-0485-4dcd-864f-5547c7d1ae00"));
        TasksResponse response = TasksResponseMother.withTasks(task1, task2);

        Collection<UUID> ids = response.getTaskIds();

        assertThat(ids).containsExactly(task1.getId(), task2.getId());
    }

    @Test
    void shouldDeserialize() throws JsonProcessingException {
        TasksResponse response = MAPPER.readValue(JSON, TasksResponse.class);

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(RESPONSE);
    }
}
