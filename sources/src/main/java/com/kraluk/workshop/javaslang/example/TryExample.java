package com.kraluk.workshop.javaslang.example;

import com.kraluk.workshop.javaslang.common.enums.Result;
import com.kraluk.workshop.javaslang.common.exception.WorkshopException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class TryExample {
    private static final Logger log = LoggerFactory.getLogger(TryExample.class);

    public static Result tryWithResult() {
        return Try.of(TryExample::someWork)
            .recover(e -> Match(e).of(
                Case(instanceOf(IllegalArgumentException.class), Result.FIRST),
                Case(instanceOf(FileNotFoundException.class), Result.SECOND),
                Case(instanceOf(NullPointerException.class), Result.SECOND_OR_THIRD)
            ))
            .getOrElse(Result.NAN);
    }

    public static Object tryWithFailureType() {
        Try<Object> operation = Try.of(TryExample::someWork);

        return Match(operation).of(
            Case(Success($(1)), "Sukces, ktorego nie bedzie :-("),
            Case(Success($()), "Domyslny sukces!"),
            Case(Success($(e -> e instanceof Integer && (Integer) e > 500)),
                e -> String.format("[][][]%s[][][]", e)),
            Case(Failure($(instanceOf(WorkshopException.class))), "Lorkszopowy blad!"),
            Case(Failure($()), "Domyslny blad!")
        );
    }

    private static Result someWork() {
        throw new IllegalArgumentException("Hello!");
    }
}