package uk.co.mruoc.camunda.client.header;

import java.net.http.HttpRequest;

public interface HeaderPopulator {

    HttpRequest.Builder populate(HttpRequest.Builder builder);

}
