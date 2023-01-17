package uk.co.mruoc.camunda.client.deploy.get;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.camunda.client.Link;

@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class Deployment {

    private final Collection<Link> links;
    private final UUID id;
    private final String name;
    private final String source;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime deploymentTime;

    private final String tenantId;
}
