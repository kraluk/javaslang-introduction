package pl.edu.uj.workshop.vavr.tasks.p1;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DistinctionValidatorTaskTest {

    @Test
    void testValidateDistinction() {
        // Given
        final String name = "Jan Kovalsky";
        final Integer currentYear = 3;
        final Seq<Double> grades = List.of(5.0, 5.0, 5.0, 5.0, 4.5);
        final Seq<String> reprimands = List.empty();

        // When
        final Validation<Seq<String>, ProposedDistinction>
            result =
            DistinctionValidatorTask.validateDistinction(name, currentYear, grades, reprimands);

        // Then
        assertThat(result.get())
            .isNotNull()
            .matches(d -> "Jan Kovalsky".equals(d.getName()), "should have a proper name")
            .matches(d -> 3 == d.getCurrentYear(), "should be on a proper year")
            .matches(d -> 4.75 <= d.getAverageGrade(), "should have a proper average grade")
            .matches(d -> 0 == d.getNumberOfReprimands(), "should have 0 reprimands");
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(result::getError);
    }

    @Test
    void testValidateDistinctionWithInvalidName() {
        // Given
        final String name = "Jan Kovalsky_555";
        final Integer currentYear = 3;
        final Seq<Double> grades = List.of(5.0, 5.0, 5.0, 5.0, 4.5);
        final Seq<String> reprimands = List.empty();

        // When
        final Validation<Seq<String>, ProposedDistinction>
            result =
            DistinctionValidatorTask.validateDistinction(name, currentYear, grades, reprimands);

        // Then
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(result::get);
        assertThat(result.getError())
            .isNotEmpty()
            .hasSize(1)
            .containsExactly("Name contains invalid characters: '5_'");
    }

    @Test
    void testValidateDistinctionWithInvalidYear() {
        // Given
        final String name = "Jan Kovalsky";
        final Integer currentYear = 1;
        final Seq<Double> grades = List.of(5.0, 5.0, 5.0, 5.0, 4.5);
        final Seq<String> reprimands = List.empty();

        // When
        final Validation<Seq<String>, ProposedDistinction>
            result =
            DistinctionValidatorTask.validateDistinction(name, currentYear, grades, reprimands);

        // Then
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(result::get);
        assertThat(result.getError())
            .isNotEmpty()
            .hasSize(1)
            .containsExactly("The given year '1' is not allowed!");
    }

    @Test
    void testValidateDistinctionWithInvalidAverageGrade() {
        // Given
        final String name = "Jan Kovalsky";
        final Integer currentYear = 3;
        final Seq<Double> grades = List.of(1.0, 1.0, 1.0, 1.0, 1.5);
        final Seq<String> reprimands = List.empty();

        // When
        final Validation<Seq<String>, ProposedDistinction>
            result =
            DistinctionValidatorTask.validateDistinction(name, currentYear, grades, reprimands);

        // Then
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(result::get);
        assertThat(result.getError())
            .isNotEmpty()
            .hasSize(1)
            .containsExactly("Given average grade is too low!");
    }

    @Test
    void testValidateDistinctionWithInvalidNumberOfReprimands() {
        // Given
        final String name = "Jan Kovalsky";
        final Integer currentYear = 3;
        final Seq<Double> grades = List.of(5.0, 5.0, 5.0, 5.0, 4.5);
        final Seq<String> reprimands = List.of("REPRIMAND!");

        // When
        final Validation<Seq<String>, ProposedDistinction>
            result =
            DistinctionValidatorTask.validateDistinction(name, currentYear, grades, reprimands);

        // Then
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(result::get);
        assertThat(result.getError())
            .isNotEmpty()
            .hasSize(1)
            .containsExactly("Student with any reprimand cannot be distincted!");
    }

    @Test
    void testValidateDistinctionWithInvalidAllData() {
        // Given
        final String name = "Jan Kovalsky222";
        final Integer currentYear = 1;
        final Seq<Double> grades = List.of(1.0, 1.0, 1.0, 1.0, 1.5);
        final Seq<String> reprimands = List.of("REPRIMAND!");

        // When
        final Validation<Seq<String>, ProposedDistinction>
            result =
            DistinctionValidatorTask.validateDistinction(name, currentYear, grades, reprimands);

        // Then
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(result::get);
        assertThat(result.getError())
            .isNotEmpty()
            .hasSize(4);
    }
}