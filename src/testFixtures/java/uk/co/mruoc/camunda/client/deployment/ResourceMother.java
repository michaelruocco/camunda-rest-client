package uk.co.mruoc.camunda.client.deployment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceMother {

    public static Collection<Resource> buildInlineScriptDemoResources() {
        return Collections.singleton(buildFromClasspath("bpmns/inline-script-demo.bpmn"));
    }

    public static Collection<Resource> buildExternalScriptDemoResources() {
        return Arrays.asList(
                buildFromClasspath("bpmns/external-script-demo.bpmn"),
                buildFromClasspath("bpmns/external-script.groovy")
        );
    }

    public static Resource buildFromClasspath(String resourceName) {
        try {
            return build(Paths.get(ClassLoader.getSystemResource(resourceName).toURI()));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(resourceName);
        }
    }

    public static Resource build(Path path) {
        return new Resource(path);
    }

}
