package uk.co.mruoc.camunda.client.process.get.variable;

import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VariableInstanceResponseJsonMother {

    public static String build() {
        return loadContentFromClasspath("json/variable-instance-response.json");
    }
}
