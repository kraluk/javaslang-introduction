package pl.edu.uj.workshop.vavr.tasks.p4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.vavr.Lazy;

import static java.time.Duration.ofMillis;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class CommandTaskTest {

    @Test
    void testGetCommandWithoutInvoking() {
        assertTimeout(ofMillis(200), () -> {
            // When
            Lazy<Double> result = CommandTask.getCommand();

            // Then
            assertThat(result.isEvaluated()).isFalse();
            assertThat(result.isLazy()).isTrue();
        });
    }

    @Test
    @Disabled("should fail when active due to invoked operations")
    void testGetCommandWithInvoking() {
        assertTimeout(ofMillis(200), () -> {
            // Given
            Lazy<Double> command = CommandTask.getCommand();

            // When
            command.get();

            // Then
            assertThat(command.isEvaluated()).isTrue();
            assertThat(command.isLazy()).isTrue();
        });
    }
}