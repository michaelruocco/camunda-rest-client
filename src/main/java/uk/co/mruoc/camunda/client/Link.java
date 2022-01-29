package uk.co.mruoc.camunda.client;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class Link {

    private final String method;
    private final String href;
    private final String rel;
}
