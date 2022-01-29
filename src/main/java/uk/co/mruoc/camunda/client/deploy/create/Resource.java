package uk.co.mruoc.camunda.client.deploy.create;

import java.nio.file.Path;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Resource {

    private final String name;
    private final Path file;

    public Resource(Path path) {
        this(path.getFileName().toString(), path);
    }
}
