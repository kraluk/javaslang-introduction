package pl.edu.uj.workshop.vavr.tasks.p3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeavyAndComplexServiceTest {

    private static final int PROPER_ID = 9;
    private static final int UNKNOWN_ID = 0;

    @Test
    void testGetData() {
        // When
        final String result = HeavyAndComplexService.getData(PROPER_ID);

        // Then
        assertThat(result).isNotBlank();
    }

    @Test
    void testGetDataForUnknownId() {
        // When
        final String result = HeavyAndComplexService.getData(UNKNOWN_ID);

        // Then
        assertThat(result).isNotBlank();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void testGetDataWithIllegalArgumentException(int requestedId) {
        assertThrows(IllegalArgumentException.class,
            () -> HeavyAndComplexService.getData(requestedId));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4})
    void testGetDataWithNullPointerException(int requestedId) {
        assertThrows(NullPointerException.class,
            () -> HeavyAndComplexService.getData(requestedId));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6})
    void testGetDataWithIllegalStateException(int requestedId) {
        assertThrows(IllegalStateException.class,
            () -> HeavyAndComplexService.getData(requestedId));
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8})
    void testGetDataWithUnsupportedOperationException(int requestedId) {
        assertThrows(UnsupportedOperationException.class,
            () -> HeavyAndComplexService.getData(requestedId));
    }
}