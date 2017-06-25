package com.kraluk.workshop.vavr.example;

/**
 * Extended Collections Examples
 *
 * @author lukasz
 */
public class ExtendedCollectionExamples {

    public static java.util.List<Integer> filterValues(int... ints) {
        return io.vavr.collection.List.ofAll(ints)
            .filter(e -> e % 2 == 0)
            .toJavaList();
    }

    public static java.util.List<Double> getSomeRandomDoubles() {
        java.util.List<Double> doubles = new java.util.ArrayList<>();

        for (double random : io.vavr.collection.Stream.continually(Math::random)
            .take(666)
            .filter(e -> e > 0.5 && e < 0.6)) {
            doubles.add(random);
        }

        return doubles;
    }
}