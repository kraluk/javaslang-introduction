package com.kraluk.workshop.vavr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vavr.Lazy;

/**
 * Lazy Examples
 *
 * @author lukasz
 */
public final class LazyExamples {

    private LazyExamples() {
    }

    public static void main(String[] args) {

    }

    private static String doWork() {
        System.out.println("INVOKED!");
        return "WORK";
    }
}