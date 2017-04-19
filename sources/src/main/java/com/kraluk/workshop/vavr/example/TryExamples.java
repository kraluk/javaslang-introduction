package com.kraluk.workshop.vavr.example;

import com.kraluk.workshop.vavr.common.enums.Result;
import com.kraluk.workshop.vavr.common.exception.WorkshopException;

import java.io.FileNotFoundException;

import javaslang.control.Try;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.Patterns.Failure;
import static javaslang.Patterns.Success;
import static javaslang.Predicates.instanceOf;

/**
 * Try Examples
 *
 * @author lukasz
 */
public class TryExamples {

    public static Result tryWithResult(Try.CheckedSupplier<Result> supplier) {
        return Try.of(supplier)
            .recover(e -> Match(e).of(
                Case(instanceOf(IllegalArgumentException.class), Result.FIRST),
                Case(instanceOf(FileNotFoundException.class), Result.SECOND),
                Case(instanceOf(NullPointerException.class), Result.SECOND_OR_THIRD)
            ))
            .getOrElse(Result.NAN);
    }

    public static Object tryWithFailureType(Try.CheckedSupplier<Object> supplier) {
        Try<Object> operation = Try.of(supplier);

        return Match(operation).of(
            Case(Success($(1)), "Sukces, ktorego nie bedzie :-("),
            Case(Success($(e -> e instanceof Double && (Double) e > 500.00)),
                Integer.MAX_VALUE),
            Case(Success($()), "Domyslny sukces!"),

            Case(Failure($(instanceOf(WorkshopException.class))), "Lorkszopowy blad!"),
            Case(Failure($()), "Domyslny blad!")
        );
    }
}