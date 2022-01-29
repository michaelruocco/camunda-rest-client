package uk.co.mruoc.camunda.client.header;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompositeHeaderPopulator implements HeaderPopulator {

    private final Collection<HeaderPopulator> populators;

    public CompositeHeaderPopulator(HeaderPopulator... populators) {
        this(Arrays.asList(populators));
    }

    @Override
    public HttpRequest.Builder populate(HttpRequest.Builder builder) {
        populators.forEach(populator -> populator.populate(builder));
        return builder;
    }
}
