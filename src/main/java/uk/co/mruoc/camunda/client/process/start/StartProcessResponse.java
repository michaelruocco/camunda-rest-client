package uk.co.mruoc.camunda.client.process.start;

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
public class StartProcessResponse {

    private final Collection<Link> links;
    private final UUID id;
    private final String definitionId;
    private final String businessKey;
    private final String caseInstanceId;
    private final boolean ended;
    private final boolean suspended;
    private final String tenantId;
}
