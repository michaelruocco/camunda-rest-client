package uk.co.mruoc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.Instant;

@RequiredArgsConstructor
@Slf4j
public class ReplaceMe {

    private final Clock clock;

    public String getValue(String input) {
        log.info("in get value");
        return String.format("replace-me-%s", input);
    }

    public Instant getTime() {
        log.info("in get time");
        return clock.instant();
    }

}
