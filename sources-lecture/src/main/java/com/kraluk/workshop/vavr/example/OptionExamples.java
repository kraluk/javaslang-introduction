package com.kraluk.workshop.vavr.example;

import com.kraluk.workshop.vavr.common.exception.WorkshopException;

import io.vavr.control.Option;

/**
 * Option Examples
 *
 * @author lukasz
 */
public final class OptionExamples {

    private OptionExamples() {
    }

    public static Integer useOption(Integer value) {
        Option<Integer> option = Option.of(value);

        return option.getOrElseThrow(() -> new WorkshopException("Can't."));
    }
}