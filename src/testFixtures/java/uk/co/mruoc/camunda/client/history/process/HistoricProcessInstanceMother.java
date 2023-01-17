package uk.co.mruoc.camunda.client.history.process;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.OffsetDateTimeParser;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoricProcessInstanceMother {

    public static HistoricProcessInstance build() {
        return builder().build();
    }

    private static HistoricProcessInstance.HistoricProcessInstanceBuilder builder() {
        return HistoricProcessInstance.builder()
                .id("7c80cc8f-ef95-11e6-b6e6-34f39ab71d4e")
                .processDefinitionId("invoice:1:7bf79f13-ef95-11e6-b6e6-34f39ab71d4e")
                .processDefinitionKey("invoice")
                .processDefinitionName("Invoice Receipt")
                .processDefinitionVersion(1)
                .startTime(OffsetDateTimeParser.parse("2017-02-10T14:33:19.000+0200"))
                .startActivityId("StartEvent_1")
                .rootProcessInstanceId("f8259e5d-ab9d-11e8-8449-e4a7a094a9d6")
                .state("ACTIVE");
    }
}
