package chapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith(BenchmarkExtension.class)
public abstract class LexicographicalOrderAbstractTest {

    private static final Duration TEST_TIMEOUT = Duration.of(1000, ChronoUnit.MILLIS);
    private static final Duration LONGTEST_TIMEOUT = Duration.of(2000, ChronoUnit.MILLIS);

    public abstract LexicographicalOrder getSut();

    @Test
    void warmUp() {
        final Optional<String> actual = assertTimeoutPreemptively(TEST_TIMEOUT, () -> getSut().nextLargerWord("ab"));
        assertEquals("ba", actual.get(), "Easier");
    }

    @Test
    void warmupToo() {
        final Optional<String> actual = assertTimeoutPreemptively(TEST_TIMEOUT, () -> getSut().nextLargerWord("cd"));
        assertEquals("dc", actual.get(), "Easy");
    }

    @ParameterizedTest(name = "{index} - Next larger word of {0} = {1}")
    @CsvSource({
        "ab, ba",
        "bb, «no answer»",
        "hefg, hegf",
        "dhck, dhkc",
        "dkhc, hcdk"
    })
    void extraLongSuperDigits(String word, String rawExpected) {
        try {
            Optional<String> expected = Optional.of(rawExpected).filter(result -> !result.equals("«no answer»"));
            final Optional<String> actual = assertTimeoutPreemptively(LONGTEST_TIMEOUT,
                () -> getSut().nextLargerWord(word));
            assertEquals(expected, actual, "Ooooh, a non matching result!");
        } catch (Throwable throwable) {
            treatKnownCauses(throwable);
        }
    }

    private void treatKnownCauses(Throwable throwable) {
        try {
            throw throwable;
        } catch (NumberFormatException ex) {
            assertEquals("Too long to be Long/Integer? ;)", NumberFormatException.class.getCanonicalName());
        } catch (Exception ex) {
            assertEquals("Prepare for the unexpected", ex.getMessage());
        } catch (OutOfMemoryError err) {
            assertEquals("Is it a Java Heap space error or did it take too long? :)", err.toString());
        } catch (Error failed) {
            throw failed;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
