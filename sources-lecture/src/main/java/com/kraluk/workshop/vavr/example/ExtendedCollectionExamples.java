package com.kraluk.workshop.vavr.example;

/**
 * Extended Collections Examples
 *
 * @author lukasz
 */
public final class ExtendedCollectionExamples {

    private static final int RANDOM_CHUNK = 666;

    private ExtendedCollectionExamples() {
    }

    public static java.util.List<Integer> filterValues(int... ints) {
        return io.vavr.collection.List.ofAll(ints)
            .filter(e -> e % 2 == 0)
            .toJavaList();
    }

    public static java.util.List<Double> getSomeRandomDoubles() {
        return io.vavr.collection.Stream.continually(Math::random)
            .take(RANDOM_CHUNK)
            .filter(e -> e > 0.5 && e < 0.6)
            .toJavaList();
    }
}