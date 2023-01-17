package uk.co.mruoc.camunda.client.history.process;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.file.FileLoader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoricProcessInstancesResponseJsonMother {

    public static String build() {
        return FileLoader.loadContentFromClasspath("json/historic-process-instances-response.json");
    }
}
