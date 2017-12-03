package com.kraluk.workshop.vavr.example;

import com.kraluk.workshop.vavr.common.enums.Result;
import com.kraluk.workshop.vavr.common.exception.WorkshopException;

import java.util.function.Supplier;

import io.vavr.CheckedFunction0;
import io.vavr.control.Try;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;
import static io.vavr.Predicates.instanceOf;

/**
 * Try Examples
 *
 * @author lukasz
 */
public final class TryExamples {

    private TryExamples() {
    }

    public static Result tryWithResult(Supplier<Result> supplier) {
        return Try.ofSupplier(supplier)
            .recover(e -> Match(e).of(
                Case($(instanceOf(IllegalArgumentException.class)), Result.FIRST),
                Case($(instanceOf(ArithmeticException.class)), Result.SECOND),
                Case($(instanceOf(NullPointerException.class)), Result.SECOND_OR_THIRD)
            ))
            .getOrElse(Result.NAN);
    }

    public static Object tryWithFailureType(CheckedFunction0<Result> workProvider) {
        Try<Object> operation = Try.of(workProvider);

        return Match(operation).of(
            Case($Success($(1)), "Success which doesn't appear :-("),
            Case($Success($()), "Default success!"),
            Case($Success($(e -> e instanceof Integer && (Integer) e > 500)),
                e -> String.format("Lucky number '%s'", e)),
            Case($Failure($(instanceOf(WorkshopException.class))), "Error!"),
            Case($Failure($()), "Default failure!")
        );
    }
}