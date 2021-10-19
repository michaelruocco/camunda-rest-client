package uk.co.mruoc.camunda.client.header;

import java.net.http.HttpRequest;

public class NoopHeaderPopulator implements HeaderPopulator {

    @Override
    public HttpRequest.Builder populate(HttpRequest.Builder builder) {
        return builder;
    }

}
