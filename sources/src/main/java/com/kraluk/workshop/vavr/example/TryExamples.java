package com.kraluk.workshop.vavr.example;

import com.kraluk.workshop.vavr.common.enums.Result;

import java.util.function.Supplier;

import io.vavr.control.Try;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

/**
 * Try Examples
 *
 * @author lukasz
 */
public class TryExamples {

    public static Result tryWithResult(Supplier<Result> supplier) {
        return Try.ofSupplier(supplier)
            .recover(e -> Match(e).of(
                Case($(instanceOf(IllegalArgumentException.class)), Result.FIRST),
                Case($(instanceOf(ArithmeticException.class)), Result.SECOND),
                Case($(instanceOf(NullPointerException.class)), Result.SECOND_OR_THIRD)
            ))
            .getOrElse(Result.NAN);
    }
}