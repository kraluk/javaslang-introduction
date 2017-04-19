package com.kraluk.workshop.vavr.task;

import javaslang.control.Option;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.Predicates.is;
import static javaslang.Predicates.isIn;

/**
 * Proposed solution of the Task 3
 *
 * @author lukasz
 */
public class MatchTask {

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
                    return "NOT_OK";
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
            Case(is("A"), o -> Match(importanceFlag).of(

                Case(is(true), f -> Match(code).of(

                    Case(is(0), c -> Match(value).of(
                        Case(v -> v > 0, "OK"),
                        Case(v -> v == 0, "RTM"),
                        Case($(), "OMG")
                    )),

                    Case(isIn(1, 2), "YOLO"),
                    Case($(), "NOT_OK")
                )),

                Case(is(false), "SO_BAD")
            )),
            Case(isIn("y", "Y"), "YOLO")
        );
    }
}