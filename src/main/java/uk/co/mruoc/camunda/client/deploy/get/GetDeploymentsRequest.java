package uk.co.mruoc.camunda.client.deploy.get;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

@Builder
@Data
public class GetDeploymentsRequest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxxx");

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime before;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSxx")
    private final OffsetDateTime after;

    private final String overrideBaseUri;

    public String toQueryString() {
        String queryString = URLEncodedUtils.format(toNameValuePairs(), StandardCharsets.UTF_8);
        if (queryString.isEmpty()) {
            return queryString;
        }
        return String.format("?%s", queryString);
    }

    public Optional<String> getOverrideBaseUri() {
        return Optional.ofNullable(overrideBaseUri);
    }

    private Collection<NameValuePair> toNameValuePairs() {
        Collection<NameValuePair> pairs = new ArrayList<>();
        toNameValuePair("before", before).ifPresent(pairs::add);
        toNameValuePair("after", after).ifPresent(pairs::add);
        return pairs;
    }

    private static Optional<NameValuePair> toNameValuePair(String name, OffsetDateTime value) {
        return Optional.ofNullable(value).map(v -> new BasicNameValuePair(name, format(v)));
    }

    private static String format(OffsetDateTime value) {
        return DATE_TIME_FORMATTER.format(value);
    }
}
