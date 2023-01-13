package uk.co.mruoc.camunda.client.history.variable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.file.FileLoader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoricVariableInstanceResponseJsonMother {

    public static String build() {
        return FileLoader.loadContentFromClasspath("json/historic-variable-instance-response.json");
    }
}
