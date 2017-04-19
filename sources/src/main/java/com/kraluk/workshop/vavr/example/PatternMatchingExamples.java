package com.kraluk.workshop.vavr.example;

import com.kraluk.workshop.vavr.common.enums.Result;

import javaslang.collection.Stream;
import javaslang.control.Option;

import static com.kraluk.workshop.vavr.common.enums.Result.FIRST;
import static com.kraluk.workshop.vavr.common.enums.Result.FORTH;
import static com.kraluk.workshop.vavr.common.enums.Result.NAN;
import static com.kraluk.workshop.vavr.common.enums.Result.SECOND;
import static com.kraluk.workshop.vavr.common.enums.Result.SECOND_OR_THIRD;
import static com.kraluk.workshop.vavr.common.enums.Result.YOLO;
import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.API.run;
import static javaslang.Predicates.anyOf;
import static javaslang.Predicates.instanceOf;
import static javaslang.Predicates.is;
import static javaslang.Predicates.isIn;

/**
 * Pattern Matching Examples
 *
 * @author lukasz
 */
public class PatternMatchingExamples {

    public static Result simpleMatching(int index) {
        return Match(index).of(
            Case($(1), FIRST),
            Case($(2), SECOND),
            Case($(), NAN)
        );
    }

    public static Result simpleMatchingWithoutDefault(int index) {
        return Match(index).of(
            Case($(1), FIRST),
            Case($(2), SECOND)
        );
    }

    public static Option<Result> simpleMatchingWithOption(int index) {
        return Match(index).option(
            Case($(1), FIRST),
            Case($(2), SECOND)
        );
    }

    public static Result simpleMatchingWithPredicate(int index) {
        return Match(index).of(
            Case(e -> e == 1, FIRST),
            Case(e -> e == 2, SECOND),
            Case($(), NAN)
        );
    }

    public static Result simpleMatchingWithComplexPredicate(Object index) {
        return Match(index).of(
            Case(instanceOf(String.class), YOLO),
            Case(is(1), FIRST),
            Case(isIn(2, 3), SECOND_OR_THIRD),
            Case(anyOf(is(4), is(99)), FORTH),
            Case($(), NAN)
        );
    }

    public static Result simpleMatchingWithValue(Object index) {
        return Match(index).of(
            Case($(1), e -> Result.values()[e + 1]),
            Case($(2), () -> SECOND),
            Case($(), NAN)
        );
    }

    public static void simpleMatchingWithWork(int index) {
        Match(index).of(
            Case($(1), e -> run(() -> System.out.println(e))),
            Case($(2), e -> run(() -> System.out.println(e))),
            Case($(42), e -> run(() -> System.out.println("Hello from run()!"))),
            Case($(), () -> null)
        );
    }

    public static void complexMatchingWithWork(int index) {
        Match(index).of(
            Case($(1), e -> run(() -> System.out.println("One"))),
            Case($(2), e -> run(() -> System.out.println("2"))),
            Case($(42), e -> run(() -> System.out.println("Hello from run(42)!"))),

            Case(isIn(Stream.from(555).take(300).toJavaArray()), e -> Match(e).of(
                Case(f -> f < 555, f -> run(() -> System.out.println("< 500"))),
                Case(isIn(Stream.from(1).take(1000)),
                    f -> run(() -> System.out.println("Almost the deepest..."))),

                Case(f -> f > 777, f -> Match(f).of(
                    Case($(), g -> run(() -> System.out.println("Grater than 777")))
                ))
            )),
            Case($(), () -> null)
        );
    }
}