package com.kraluk.workshop.vavr.old.task;

import io.vavr.control.Option;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.is;
import static io.vavr.Predicates.isIn;

/**
 * Proposed solution of the Task 3
 *
 * @author lukasz
 */
public final class MatchTask {

    private MatchTask() {
    }

    public static String doSomeComplexLogic(final String additionalOptions,
                                            final boolean importanceFlag,
                                            final int code,
                                            final double value) {
        if ("A".equals(additionalOptions)) {
            if (importanceFlag) {
                if (code == 0) {
                    if (value > 0) {
                        return "OK";
                    } else if (value == 0) {
                        return "RTM";
                    } else {
                        return "OMG";
                    }
                } else if (code == 1 || code == 2) {
                    return "YOLO";
                } else {
                    return "NOT_OK"; // TODO: not tested case!
                }
            } else {
                return "SO_BAD";
            }
        } else if ("Y".equalsIgnoreCase(additionalOptions)) {
            return "YOLO";
        }

        return null;
    }

    public static Option<String> doSomeComplexLogicInBetterWay(final String additionalOptions,
                                                               final boolean importanceFlag,
                                                               final int code,
                                                               final double value) {
        return Match(additionalOptions).option(
            Case($(is("A")), o -> Match(importanceFlag).of(

                Case($(is(true)), f -> Match(code).of(

                    Case($(is(0)), c -> Match(value).of(
                        Case($(v -> v > 0), "OK"),
                        Case($(v -> v == 0), "RTM"),
                        Case($(), "OMG")
                    )),

                    Case($(isIn(1, 2)), "YOLO"),
                    Case($(), "NOT_OK")
                )),

                Case($(is(false)), "SO_BAD")
            )),
            Case($(isIn("y", "Y")), "YOLO")
        );
    }
}