package com.kraluk.workshop.vavr.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import javaslang.control.Try;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.Patterns.Failure;
import static javaslang.Patterns.Success;
import static javaslang.Predicates.instanceOf;

/**
 * Proposed solution of the Task 4
 *
 * @author lukasz
 */
public class TryTask {
    private static final List<String> IO_ERROR = Collections.singletonList("IO Error occurred!");
    private static final List<String> NPE_ERROR = Collections.singletonList("NPE occurred!");

    public static List<String> getResult(final String path) {

        return Try.of(() -> Files.readAllLines(Paths.get(path)))
            .recover(e -> Match(e).of(
                Case(instanceOf(IOException.class), () -> IO_ERROR),
                Case(instanceOf(NullPointerException.class), () -> NPE_ERROR)
            ))
            .andThen(Collections::unmodifiableList)
            .getOrElse(Collections.emptyList());
    }

    public static List<String> getResultInAnotherWay(final String path) {

        Try<List<String>> recover = Try.of(() -> Files.readAllLines(Paths.get(path)));

        return Match(recover).of(
            Case(Success($()), Collections::unmodifiableList),
            Case(Failure($(instanceOf(IOException.class))), IO_ERROR),
            Case(Failure($(instanceOf(NullPointerException.class))), NPE_ERROR),
            Case(Failure($()), Collections::emptyList)
        );
    }
}
