package uk.co.mruoc.camunda.client.header;

import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.List;

public class CompositeHeaderPopulator implements HeaderPopulator {

    private final Collection<HeaderPopulator> populators;

    public CompositeHeaderPopulator(HeaderPopulator... populators) {
        this(List.of(populators));
    }

    public CompositeHeaderPopulator(Collection<HeaderPopulator> populators) {
        this.populators = populators;
    }

    @Override
    public HttpRequest.Builder populate(HttpRequest.Builder builder) {
        populators.forEach(populator -> populator.populate(builder));
        return builder;
    }
}
