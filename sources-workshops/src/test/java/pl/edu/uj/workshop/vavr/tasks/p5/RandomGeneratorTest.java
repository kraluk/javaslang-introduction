package pl.edu.uj.workshop.vavr.tasks.p5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomGeneratorTest {

    /*
     * It's just a simple test, sure, it should test a much more possibilities and check if the generator provides proper values on more samples...
     */
    @Test
    void testGetChar() {
        // When
        final char result = RandomGenerator.getChar();

        // Then
        assertThat(result)
            .isUpperCase()
            .isGreaterThanOrEqualTo('A')
            .isLessThanOrEqualTo('Z');
    }
}