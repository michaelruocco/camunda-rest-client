package uk.co.mruoc.camunda.client;

import static org.testcontainers.utility.MountableFile.forHostPath;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.utility.DockerImageName;

@Slf4j
public class FakeLocalCamunda extends MockServerContainer {

    private static final int PORT = 1080;

    public FakeLocalCamunda() {
        super(DockerImageName.parse("mockserver/mockserver:latest"));
        withCopyFileToContainer(
                forHostPath("mock-server/camunda-history-responses.json"), "/camunda-history-responses.json");
        withEnv("MOCKSERVER_INITIALIZATION_JSON_PATH", "/camunda-history-responses.json");
        withExposedPorts(PORT);
        withStartupTimeout(Duration.ofMinutes(1));
        withLogConsumer(this::logInfo);
    }

    public String getUri() {
        String host = getHost();
        int port = getMappedPort(PORT);
        return String.format("http://%s:%d", host, port);
    }

    private void logInfo(OutputFrame frame) {
        log.info(removeNewline(frame.getUtf8String()));
    }

    private static String removeNewline(String value) {
        return value.replace("\n", "").replace("\r", "");
    }
}
