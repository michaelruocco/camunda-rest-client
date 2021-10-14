package uk.co.mruoc.camunda.client;

import uk.co.mruoc.camunda.client.deployment.CreateDeploymentRequest;
import uk.co.mruoc.camunda.client.deployment.Resource;
import uk.co.mruoc.camunda.client.process.StartProcessRequest;
import uk.co.mruoc.camunda.client.process.variable.StringVariable;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

public class CamundaClientRunner {

    public static void main(String[] args) {
        var client = new CamundaClient("http://localhost:8080");

        var directory = Path.of("/Users/michaelruocco/git/github/camunda-spring-boot-demo/bpmns/");
        var inlineRequest = CreateDeploymentRequest.builder()
                .deploymentName("inline-demo-deployment")
                .deploymentSource("local")
                .resources(Collections.singleton(new Resource(directory.resolve("inline-demo.bpmn"))))
                .build();
        client.createDeployment(inlineRequest);

        var externalRequest = CreateDeploymentRequest.builder()
                .deploymentName("external-demo-deployment")
                .deploymentSource("local")
                .resources(Arrays.asList(
                        new Resource(directory.resolve("external-demo.bpmn")),
                        new Resource(directory.resolve("external-demo.groovy"))
                )).build();
        client.createDeployment(externalRequest);

        var startRequest = StartProcessRequest.builder()
                .processKey("demo-external-process")
                .variables(Collections.singleton(new StringVariable("input", "hi joe bloggs")))
                .build();
        client.startProcess(startRequest);
    }

}
