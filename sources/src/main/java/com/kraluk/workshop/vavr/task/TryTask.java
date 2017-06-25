package com.kraluk.workshop.vavr.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import io.vavr.control.Try;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

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
                Case($(instanceOf(IOException.class)), () -> IO_ERROR),
                Case($(instanceOf(NullPointerException.class)), () -> NPE_ERROR)
            ))
            .andThen(Collections::unmodifiableList)
            .getOrElse(Collections.emptyList());
    }
}
