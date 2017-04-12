package com.kraluk.workshop.javaslang.example;

import com.kraluk.workshop.javaslang.common.exception.WorkshopException;

import javaslang.control.Option;

/**
 * Option Examples
 *
 * @author lukasz
 */
public class OptionExamples {

    public static Integer someSimpleOption() {
        Option<Integer> option = Option.of(null);

        return option.getOrElseThrow(() -> new WorkshopException("Can't."));
    }
}