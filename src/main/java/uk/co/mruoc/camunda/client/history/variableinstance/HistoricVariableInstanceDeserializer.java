package uk.co.mruoc.camunda.client.history.variableinstance;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import java.io.IOException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;

@Slf4j
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class HistoricVariableInstanceDeserializer extends JsonDeserializer<HistoricVariableInstance<?>>
        implements ContextualDeserializer {

    private final ObjectMapper mapper = new ObjectMapper();
    private final JavaType valueType;

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty property)
            throws JsonMappingException {
        // The BeanProperty can sometimes be null, I believe it depends on how we add
        // this deserializer to the object mapper
        JavaType genericType = deserializationContext.getContextualType() != null
                ? deserializationContext.getContextualType().containedType(0)
                : property.getType().containedType(0);
        return new HistoricVariableInstanceDeserializer(genericType);
    }

    @Override
    public HistoricVariableInstance<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = deserializationContext.readTree(jsonParser);
        String valueText = StringEscapeUtils.unescapeJson(json.get("value").asText());

        return HistoricVariableInstance.builder()
                .id(json.get("id").asText())
                .name(json.get("name").asText())
                .type(json.get("type").asText())
                .value(mapper.readValue(valueText, valueType))
                .processDefinitionKey(json.get("processDefinitionKey").asText())
                .processDefinitionId(json.get("processDefinitionId").asText())
                .processInstanceId(json.get("processInstanceId").asText())
                .executionId(json.get("executionId").asText())
                .activityInstanceId(json.get("activityInstanceId").asText())
                .caseDefinitionKey(json.get("caseDefinitionKey").asText())
                .caseDefinitionId(json.get("caseDefinitionId").asText())
                .caseInstanceId(json.get("caseInstanceId").asText())
                .caseExecutionId(json.get("caseExecutionId").asText())
                .taskId(json.get("taskId").asText())
                .tenantId(json.get("tenantId").asText())
                .errorMessage(json.get("errorMessage").asText())
                .state(json.get("state").asText())
                .createTime(json.get("createTime").asText())
                .removalTime(json.get("removalTime").asText())
                .rootProcessInstanceId(json.get("rootProcessInstanceId").asText())
                .build();
    }
}
