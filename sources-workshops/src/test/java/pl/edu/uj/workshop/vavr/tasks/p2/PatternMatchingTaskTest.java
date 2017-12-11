package pl.edu.uj.workshop.vavr.tasks.p2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PatternMatchingTaskTest {

    // --- OLD WAY

    @ParameterizedTest
    @CsvSource({
        "true, ADDITION, 22.0, 3, 6",
        "true, MULTIPLICATION, 22.0, 3, 9",
        "true, SUBTRACTION, 22.0, 3, 42",
        "true, DIVISION, 22.0, 3, 42"})
    void testCalculateForIntegers(final boolean isImportant,
                                  final OperationType operationType,
                                  final double value,
                                  final Integer number,
                                  final Integer expected) {
        // When
        Number result = PatternMatchingTask.calculate(isImportant, operationType, value, number);

        // Then
        assertThat(result)
            .isNotNull()
            .isInstanceOf(Integer.class)
            .isEqualTo(expected);
    }

    @Test
    void testCalculateForIntegersWhenValueIsNegative() {
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
            () -> PatternMatchingTask.calculate(true, OperationType.ADDITION, -22.0, 1));
    }

    @ParameterizedTest
    @CsvSource({
        "true, ADDITION, 22.0, 3.0, 6.0",
        "true, MULTIPLICATION, 22.0, 3.0, 6.0",
        "true, SUBTRACTION, 3.0, 9.0, 6.0",
        "true, DIVISION, 3.0, 9.0, 3.0"})
    void testCalculateForDoubles(final boolean isImportant,
                                 final OperationType operationType,
                                 final double value,
                                 final Double number,
                                 final Double expected) {
        // When
        Number result = PatternMatchingTask.calculate(isImportant, operationType, value, number);

        // Then
        assertThat(result)
            .isNotNull()
            .isInstanceOf(Double.class)
            .isEqualTo(expected);
    }

    @Test
    void testCalculateForDoublesImportanceFlagIsFalse() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
            () -> PatternMatchingTask.calculate(false, OperationType.ADDITION, -22.0, 1.9));
    }

    @Test
    void testCalculateForByte() {
        // When
        Number result = PatternMatchingTask.calculate(true, OperationType.ADDITION, 1.0, (byte) 2);

        // Then
        assertThat(result)
            .isNotNull()
            .isInstanceOf(Double.class)
            .isEqualTo(128.821);
    }

    @Test
    void testCalculateForAnyOtherSerializable() {
        // When
        Number result = PatternMatchingTask.calculate(true, OperationType.ADDITION, 1.0, "serializable variable");

        // Then
        assertThat(result)
            .isNull();
    }

    // --- NEW WAY

    @ParameterizedTest
    @CsvSource({
        "true, ADDITION, 22.0, 3, 6",
        "true, MULTIPLICATION, 22.0, 3, 9",
        "true, SUBTRACTION, 22.0, 3, 42",
        "true, DIVISION, 22.0, 3, 42"})
    void testCalculateInABetterWayForIntegers(final boolean isImportant,
                                              final OperationType operationType,
                                              final double value,
                                              final Integer number,
                                              final Integer expected) {
        // When
        Number result = PatternMatchingTask
            .calculateInABetterWay(isImportant, operationType, value, number);

        // Then
        assertThat(result)
            .isNotNull()
            .isInstanceOf(Integer.class)
            .isEqualTo(expected);
    }

    @Test
    void testCalculateInABetterWayForIntegersWhenValueIsNegative() {
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
            () -> PatternMatchingTask
                .calculateInABetterWay(true, OperationType.ADDITION, -22.0, 1));
    }

    @ParameterizedTest
    @CsvSource({
        "true, ADDITION, 22.0, 3.0, 6.0",
        "true, MULTIPLICATION, 22.0, 3.0, 6.0",
        "true, SUBTRACTION, 3.0, 9.0, 6.0",
        "true, DIVISION, 3.0, 9.0, 3.0"})
    void testCalculateInABetterWayForDoubles(final boolean isImportant,
                                             final OperationType operationType,
                                             final double value,
                                             final Double number,
                                             final Double expected) {
        // When
        Number result = PatternMatchingTask
            .calculateInABetterWay(isImportant, operationType, value, number);

        // Then
        assertThat(result)
            .isNotNull()
            .isInstanceOf(Double.class)
            .isEqualTo(expected);
    }

    @Test
    void testCalculateInABetterWayForDoublesImportanceFlagIsFalse() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
            () -> PatternMatchingTask
                .calculateInABetterWay(false, OperationType.ADDITION, -22.0, 1.9));
    }

    @Test
    void testCalculateInABetterWayForByte() {
        // When
        Number result = PatternMatchingTask
            .calculateInABetterWay(true, OperationType.ADDITION, 1.0, (byte) 2);

        // Then
        assertThat(result)
            .isNotNull()
            .isInstanceOf(Double.class)
            .isEqualTo(128.821);
    }

    @Test
    void testCalculateInABetterWayForAnyOtherSerializable() {
        // When
        Number result = PatternMatchingTask
            .calculateInABetterWay(true, OperationType.ADDITION, 1.0, "serializable variable");

        // Then
        assertThat(result)
            .isNull();
    }
}