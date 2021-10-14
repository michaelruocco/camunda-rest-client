package uk.co.mruoc;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReplaceMeTest {

    private final Clock clock = mock(Clock.class);

    private final ReplaceMe replaceMe = new ReplaceMe(clock);

    @Test
    void shouldReturnValue() {
        String input = "please";

        String output = replaceMe.getValue(input);

        assertThat(output).isEqualTo("replace-me-please");
    }

    @Test
    void shouldReturnTime() {
        Instant expectedTime = Instant.now();
        given(clock.instant()).willReturn(expectedTime);

        Instant time = replaceMe.getTime();

        assertThat(time).isEqualTo(expectedTime);
    }

}
