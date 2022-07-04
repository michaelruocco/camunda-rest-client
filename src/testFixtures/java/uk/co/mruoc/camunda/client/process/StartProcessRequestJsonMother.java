package uk.co.mruoc.camunda.client.process;

import static uk.co.mruoc.file.content.ContentLoader.loadContentFromClasspath;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartProcessRequestJsonMother {

    public static String build() {
        return loadContentFromClasspath("json/start-process-request.json");
    }
}
