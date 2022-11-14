package uk.co.mruoc.camunda.client.task;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.file.FileLoader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TasksResponseJsonMother {

    public static String build() {
        return FileLoader.loadContentFromClasspath("json/tasks-response.json");
    }
}
