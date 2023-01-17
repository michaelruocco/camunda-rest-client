package uk.co.mruoc.camunda.client.history.variable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.OffsetDateTimeParser;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoricVariableInstanceMother {

    public static HistoricVariableInstance buildString() {
        return builder()
                .id("id-1")
                .name("name-1")
                .type("String")
                .value("some value")
                .build();
    }

    public static HistoricVariableInstance buildInt() {
        return builder().id("id-2").name("name-2").type("Integer").value(10).build();
    }

    public static HistoricVariableInstance.HistoricVariableInstanceBuilder builder() {
        return HistoricVariableInstance.builder()
                .valueInfo(new ValueInfo())
                .processInstanceId("aVariableInstanceProcInstId")
                .processDefinitionKey("aVariableInstanceProcDefKey")
                .processDefinitionId("aVariableInstanceProcDefId")
                .executionId("aVariableInstanceExecutionId")
                .activityInstanceId("aVariableInstanceActivityInstId")
                .state("CREATED")
                .createTime(OffsetDateTimeParser.parse("2017-02-10T14:33:19.000+0200"))
                .removalTime(OffsetDateTimeParser.parse("2018-02-10T14:33:19.000+0200"))
                .rootProcessInstanceId("aRootProcessInstanceId");
    }
}
