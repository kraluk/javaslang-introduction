package pl.edu.uj.workshop.vavr.tasks.p5;

import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;

public final class PalindromeTask {

    private static final String JOINER = "";
    private static final List<String> EMERGENCY_RESULT = List.of("abba");

    private static final Supplier<Stream<String>> STREAM_SUPPLIER =
        () -> Stream
            .continually(RandomGenerator::getChar)
            .take(100)
            .map(String::valueOf);

    private PalindromeTask() {
    }

    public static java.util.List<String> getPalindromes() {
        return STREAM_SUPPLIER.get()
            .zip(STREAM_SUPPLIER.get())
            .map(PalindromeTask::toString)
            .crossProduct()
            .toStream()
            .map(PalindromeTask::toString)
            .filter(PalindromeTask::isPalindrome)
            .map(String::toLowerCase)
            .distinct()
            .sorted()
            .peek(System.out::println)
            .orElse(EMERGENCY_RESULT)
            .toJavaList();
    }

    public static java.util.List<String> getPalindromesInAnotherWay() {
        return Stream
            .continually(RandomGenerator::getChar)
            .take(20)
            .map(String::valueOf)
            .crossProduct(4)
            .toStream()
            .map(PalindromeTask::toString)
            .filter(PalindromeTask::isPalindrome)
            .map(String::toLowerCase)
            .distinct()
            .sorted()
            .peek(System.out::println)
            .orElse(EMERGENCY_RESULT)
            .toJavaList();
    }

    private static String toString(Tuple2<String, String> tuple) {
        return new StringJoiner(JOINER).add(tuple._1()).add(tuple._2()).toString();
    }

    private static String toString(Seq<String> seq) {
        return seq.collect(Collectors.joining());
    }

    private static boolean isPalindrome(final String text) {
        return text.equals(new StringBuilder(text).reverse().toString());
    }
}
