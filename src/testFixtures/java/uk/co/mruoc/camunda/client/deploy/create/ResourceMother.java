package uk.co.mruoc.camunda.client.deploy.create;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceMother {

    private static final String BPMN_DIRECTORY_PATH = "./bpmns";

    public static Collection<Resource> buildExternalScriptDemoResources() {
        return Arrays.asList(
                buildFromBpmnDirectory("external-script-demo.bpmn"), buildFromBpmnDirectory("external-script.groovy"));
    }

    public static Collection<Resource> buildInlineScriptDemoResources() {
        return buildInlineScriptResources("inline-script-demo.bpmn");
    }

    public static Collection<Resource> buildInlineScriptResources(String filename) {
        return Collections.singleton(buildFromBpmnDirectory(filename));
    }

    public static Collection<Resource> build(Path... paths) {
        return build(Arrays.asList(paths));
    }

    public static Collection<Resource> build(Collection<Path> paths) {
        return paths.stream().map(ResourceMother::build).collect(Collectors.toList());
    }

    public static Resource build(Path path) {
        return new Resource(path);
    }

    private static Resource buildFromBpmnDirectory(String fileName) {
        return build(Paths.get(BPMN_DIRECTORY_PATH, fileName));
    }
}
