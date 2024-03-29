package uk.co.mruoc.camunda.client;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.OutputFrame;

@Slf4j
public class LocalCamunda extends GenericContainer<LocalCamunda> {

    private static final int PORT = 8080;

    public LocalCamunda() {
        super("camunda/camunda-bpm-platform:7.18.0");
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
