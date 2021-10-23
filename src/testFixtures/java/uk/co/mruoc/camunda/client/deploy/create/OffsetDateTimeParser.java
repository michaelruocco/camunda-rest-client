package uk.co.mruoc.camunda.client.deploy.create;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OffsetDateTimeParser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public static OffsetDateTime parse(String text) {
        return OffsetDateTime.parse(text, DATE_TIME_FORMATTER);
    }

}
