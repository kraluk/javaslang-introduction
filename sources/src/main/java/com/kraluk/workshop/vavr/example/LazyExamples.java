package com.kraluk.workshop.vavr.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javaslang.Lazy;

/**
 * Lazy Examples
 *
 * @author lukasz
 */
public class LazyExamples {
    private static final Logger log = LoggerFactory.getLogger(LazyExamples.class);

    public static Double ofLazy() {
        Lazy<Double> lazy = Lazy.of(Math::random);

        log.info("{}", lazy.isEvaluated());
        log.info("{}", lazy.get());

        return lazy.get();
    }

    public static void valLazy() {
        CharSequence value = Lazy.val(LazyExamples::printSomething, CharSequence.class);

        log.info("{}", value);
    }

    private static String printSomething() {
        return "RTM!";
    }
}