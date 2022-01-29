package uk.co.mruoc.camunda.client.deploy.delete;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

@RequiredArgsConstructor
@Builder
@Data
public class DeleteDeploymentRequest {

    private final UUID id;
    private final Boolean cascade;
    private final Boolean skipCustomListeners;
    private final String overrideBaseUri;

    public DeleteDeploymentRequest(UUID id) {
        this(id, null, null, null);
    }

    public Optional<String> getOverrideBaseUri() {
        return Optional.ofNullable(overrideBaseUri);
    }

    public String toQueryString() {
        String queryString = URLEncodedUtils.format(toNameValuePairs(), StandardCharsets.UTF_8);
        if (queryString.isEmpty()) {
            return queryString;
        }
        return String.format("?%s", queryString);
    }

    private Collection<NameValuePair> toNameValuePairs() {
        Collection<NameValuePair> pairs = new ArrayList<>();
        toNameValuePair("cascade", cascade).ifPresent(pairs::add);
        toNameValuePair("skipCustomListeners", skipCustomListeners).ifPresent(pairs::add);
        return pairs;
    }

    private static Optional<NameValuePair> toNameValuePair(String name, Boolean value) {
        return Optional.ofNullable(value).map(v -> new BasicNameValuePair(name, Boolean.toString(v)));
    }

    public static class DeleteDeploymentRequestBuilder {

        // intentionally blank

    }
}
