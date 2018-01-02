package com.kraluk.workshop.vavr;

import io.vavr.control.Option;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.API.run;
import static io.vavr.Predicates.anyOf;
import static io.vavr.Predicates.is;
import static io.vavr.Predicates.isIn;

/**
 * Pattern Matching Examples
 *
 * @author lukasz
 */
public final class PatternMatchingExamples {

    private PatternMatchingExamples() {
    }

    public static String method(int index) {
        String value;

        if (index == 1) {
            value = "First";
        } else if (index == 2) {
            value = "Second";
        } else {
            value = "(empty)";
        }

        return value;
    }


    public static void main(String[] args) {

    }

}