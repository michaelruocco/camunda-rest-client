package uk.co.mruoc.camunda.client;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinkMother {

    public static Collection<Link> buildOne() {
        return Collections.singleton(build());
    }

    public static Link build() {
        return Link.builder()
                .method("GET")
                .href("http://localhost:55062/engine-rest/deployment/27664029-2e70-11ec-8938-0242ac110003")
                .rel("self")
                .build();
    }

}
