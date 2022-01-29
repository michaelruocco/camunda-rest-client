package uk.co.mruoc.camunda.client.deploy.get;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.camunda.client.deploy.get.GetDeploymentsRequest.GetDeploymentsRequestBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetDeploymentsRequestMother {

    public static GetDeploymentsRequest empty() {
        return GetDeploymentsRequest.builder().build();
    }

    public static GetDeploymentsRequest before(OffsetDateTime cutoff) {
        return GetDeploymentsRequest.builder().before(cutoff).build();
    }

    public static GetDeploymentsRequest build() {
        return builder().build();
    }

    public static GetDeploymentsRequestBuilder builder() {
        return GetDeploymentsRequest.builder()
                .before(OffsetDateTime.of(2050, 12, 31, 12, 0, 0, 0, ZoneOffset.UTC))
                .after(OffsetDateTime.of(2000, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC));
    }
}
