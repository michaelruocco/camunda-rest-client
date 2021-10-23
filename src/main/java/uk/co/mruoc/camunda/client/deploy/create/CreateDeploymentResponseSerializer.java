package uk.co.mruoc.camunda.client.deploy.create;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Collection;

public class CreateDeploymentResponseSerializer extends StdSerializer<CreateDeploymentResponse> {

    protected CreateDeploymentResponseSerializer() {
        super(CreateDeploymentResponse.class);
    }

    @Override
    public void serialize(CreateDeploymentResponse value, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeStartObject();
        provider.defaultSerializeField("links", value.getLinks(), json);
        provider.defaultSerializeField("id", value.getId(), json);
        provider.defaultSerializeField("name", value.getName(), json);
        provider.defaultSerializeField("source", value.getSource(), json);
        provider.defaultSerializeField("deploymentTime", value.getDeploymentTime(), json);
        provider.defaultSerializeField("tenantId", value.getTenantId(), json);
        write(value.getDeployedProcessDefinitions(), json, provider);
        provider.defaultSerializeField("deployedCaseDefinitions", value.getDeployedCaseDefinitions(), json);
        provider.defaultSerializeField("deployedDecisionDefinitions", value.getDeployedDecisionDefinitions(), json);
        provider.defaultSerializeField("deployedDecisionRequirementsDefinitions", value.getDeployedDecisionRequirementsDefinitions(), json);
        json.writeEndObject();
    }

    private void write(Collection<ProcessDefinition> processDefinitions, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeFieldName("deployedProcessDefinitions");
        json.writeStartObject();
        for (ProcessDefinition processDefinition : processDefinitions) {
            write(processDefinition, json, provider);
        }
        json.writeEndObject();
    }

    private void write(ProcessDefinition processDefinition, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeFieldName(processDefinition.getId().toString());
        provider.defaultSerializeValue(processDefinition, json);
    }

}
