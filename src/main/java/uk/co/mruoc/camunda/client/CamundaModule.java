package uk.co.mruoc.camunda.client;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Collections;
import uk.co.mruoc.camunda.client.variable.Variables;
import uk.co.mruoc.camunda.client.variable.VariablesSerializer;

public class CamundaModule extends SimpleModule {

    public CamundaModule() {
        super("camunda-module", Version.unknownVersion());

        addSerializer(Variables.class, new VariablesSerializer());
    }

    @Override
    public Iterable<? extends Module> getDependencies() {
        return Collections.singleton(new JavaTimeModule());
    }
}
