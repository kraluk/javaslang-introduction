package pl.edu.uj.workshop.vavr.tasks.p3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import io.vavr.control.Either;
import io.vavr.control.Option;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class EmergencyTaskTest {

    @ParameterizedTest
    @CsvSource({
        "1, IAE",
        "2, IAE",
        "3, NPE",
        "4, NPE",
        "5, ISE",
        "6, ISE",
        "7, UOE",
        "8, UOE",
        "9, SOME TOP SECRET INFORMATION",
        "0, There aren't any information in the queried scope!"})
    void testGetInformation(final int requestedId, final String expected) {
        // When
        final Option<String> result = EmergencyTask.getInformation(requestedId);

        // Then
        assertThat(result)
            .isNotEmpty()
            .containsExactly(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void testGetInformationInAnotherWayWhenExceptionIsThrown(final int requestedId) {
        // When
        final Either<Throwable, String>
            result =
            EmergencyTask.getInformationInAnotherWay(requestedId);

        // Then
        assertThat(result)
            .isNotNull();

        assertThat(result.getLeft())
            .isInstanceOf(Exception.class);

        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(result::get);
    }

    @ParameterizedTest
    @CsvSource({
        "9, SOME TOP SECRET INFORMATION",
        "0, There aren't any information in the queried scope!"})
    void testGetInformationInAnotherWay(final int requestedId, final String expected) {
        // When
        final Either<Throwable, String> result =
            EmergencyTask.getInformationInAnotherWay(requestedId);

        // Then
        assertThat(result)
            .isNotNull();

        assertThat(result.get())
            .isNotNull()
            .isEqualTo(expected);

        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(result::getLeft);
    }

    @ParameterizedTest
    @CsvSource({
        "1, IllegalArgumentException",
        "2, IllegalArgumentException",
        "3, NullPointerException",
        "4, NullPointerException",
        "5, IllegalStateException",
        "6, IllegalStateException",
        "7, UnsupportedOperationException",
        "8, UnsupportedOperationException"})
    void testGetInformationInAnotherAnotherWayWhenExceptionIsThrown(final int requestedId,
                                                                    final String expected) {
        // When
        final String result =
            EmergencyTask.getInformationInAnotherAnotherWay(requestedId);

        // Then
        assertThat(result)
            .isNotEmpty()
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "9, SOME TOP SECRET INFORMATION",
        "0, There aren't any information in the queried scope!"})
    void testGetInformationInAnotherAnotherWay(final int requestedId, final String expected) {
        // When
        final String result =
            EmergencyTask.getInformationInAnotherAnotherWay(requestedId);

        // Then
        assertThat(result)
            .isNotNull()
            .isEqualTo(expected);
    }
}