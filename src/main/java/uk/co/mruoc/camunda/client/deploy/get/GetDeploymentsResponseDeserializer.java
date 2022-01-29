package uk.co.mruoc.camunda.client.deploy.get;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.util.Collection;
import uk.co.mruoc.json.jackson.JsonNodeConverter;
import uk.co.mruoc.json.jackson.JsonParserConverter;

public class GetDeploymentsResponseDeserializer extends StdDeserializer<GetDeploymentsResponse> {

    private static final TypeReference<Collection<Deployment>> DEPLOYMENT_COLLECTION = new TypeReference<>() {
                // intentionally blank
            };

    protected GetDeploymentsResponseDeserializer() {
        super(GetDeploymentsResponse.class);
    }

    @Override
    public GetDeploymentsResponse deserialize(JsonParser parser, DeserializationContext context) {
        JsonNode node = JsonParserConverter.toNode(parser);
        return new GetDeploymentsResponse(JsonNodeConverter.toCollection(node, parser, DEPLOYMENT_COLLECTION));
    }
}
