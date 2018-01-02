package pl.edu.uj.workshop.vavr.tasks.p5;

import java.util.Random;

import io.vavr.Tuple;
import io.vavr.Tuple2;

final class RandomGenerator {

    private static final Random RANDOM = new Random();
    private static final Tuple2<Integer, Integer> ASCII_LETTERS = Tuple.of(65, 90);

    private RandomGenerator() {
    }

    static char getChar() {
        int number =
            RANDOM.nextInt((ASCII_LETTERS._2() - ASCII_LETTERS._1()) + 1) + ASCII_LETTERS._1();
        return (char) number;
    }
}
