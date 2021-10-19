package uk.co.mruoc.camunda.client.header;

import java.net.http.HttpRequest;

public class JsonContentTypePopulator implements HeaderPopulator {

    @Override
    public HttpRequest.Builder populate(HttpRequest.Builder builder) {
        builder.header("Content-Type", "application/json");
        return builder;
    }

}
