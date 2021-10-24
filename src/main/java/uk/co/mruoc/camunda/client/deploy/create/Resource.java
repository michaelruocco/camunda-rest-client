package uk.co.mruoc.camunda.client.deploy.create;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@RequiredArgsConstructor
@Data
public class Resource {

    private final String name;
    private final Path file;

    public Resource(Path path) {
        this(path.getFileName().toString(), path);
    }

}
