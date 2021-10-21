package uk.co.mruoc.camunda.client.deploy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetDeploymentsRequestMother {

    public static GetDeploymentsRequest empty() {
        return GetDeploymentsRequest.builder().build();
    }

    public static GetDeploymentsRequest build() {
        return GetDeploymentsRequest.builder()
                .before(OffsetDateTime.of(2050, 12, 31, 12, 0, 0, 0, ZoneOffset.UTC))
                .after(OffsetDateTime.of(2000, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC))
                .build();
    }

}
