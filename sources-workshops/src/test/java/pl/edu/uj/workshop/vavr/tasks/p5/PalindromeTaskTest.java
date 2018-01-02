package pl.edu.uj.workshop.vavr.tasks.p5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PalindromeTaskTest {

    @Test
    void testGetPalindromes() {
        // When
        final List<String> result = PalindromeTask.getPalindromes();

        // Then
        assertThat(result)
            .isNotNull()
            .isNotEmpty();
    }

    @Test
    void testGetPalindromesInAnotherWay() {
        // When
        final List<String> result = PalindromeTask.getPalindromesInAnotherWay();

        // Then
        assertThat(result)
            .isNotNull()
            .isNotEmpty();
    }
}