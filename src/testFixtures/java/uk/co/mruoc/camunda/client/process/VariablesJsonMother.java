package uk.co.mruoc.camunda.client.process;

import static uk.co.mruoc.file.content.ContentLoader.loadContentFromClasspath;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VariablesJsonMother {

    public static String build() {
        return loadContentFromClasspath("json/variables.json");
    }

    public static String unsupported() {
        return loadContentFromClasspath("json/unsupported-variables.json");
    }
}
