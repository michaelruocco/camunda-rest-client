package uk.co.mruoc.camunda.client.deploy.create;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.file.content.ContentLoader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateDeploymentResponseJsonMother {

    public static String build() {
        return ContentLoader.loadContentFromClasspath("json/create-deployment-response.json");
    }

}
