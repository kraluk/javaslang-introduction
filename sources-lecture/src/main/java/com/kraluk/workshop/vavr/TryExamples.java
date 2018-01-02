package com.kraluk.workshop.vavr;

import io.vavr.control.Try;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;

/**
 * Try Examples
 *
 * @author lukasz
 */
public final class TryExamples {

    private TryExamples() {
    }


    private static void handleEx() {

    }

    private static String throwEx() {
        return "xxx";
    }

    public static void main(String[] args) {
        handleEx();
    }
}