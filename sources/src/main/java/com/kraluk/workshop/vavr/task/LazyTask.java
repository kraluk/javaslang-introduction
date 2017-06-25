package com.kraluk.workshop.vavr.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.function.Supplier;

import io.vavr.Function4;
import io.vavr.Lazy;

/**
 * Proposed solution of the Task 2
 *
 * @author lukasz
 */
public class LazyTask {
    private static final Logger log = LoggerFactory.getLogger(LazyTask.class);

    public static Lazy<Double> calculate(Double x, Double a, Double b, Double c) {
        return Lazy
            .of(() -> someComplexAndHeavyMathOperation(x, a, b, c));
    }

    public static Serializable calculateInAnotherWay(Double x, Double a, Double b, Double c) {
        return Lazy
            .val(() -> someComplexAndHeavyMathOperation(x, a, b, c),
                Serializable.class);
    }

    // ------------------------------------------------------------------------------------------

    public static Serializable calculateInAlternativeWay(Double x, Double a, Double b, Double c) {

        return Lazy
            .val(bind(LazyTask::someComplexAndHeavyMathOperation, x, a, b, c),
                Serializable.class);
    }

    private static <T> Supplier<T> bind(Function4<T, T, T, T, T> fn, T x, T a, T b, T c) {
        return () -> fn.apply(x, a, b, c);
    }

    // ------------------------------------------------------------------------------------------

    public static Double someComplexAndHeavyMathOperation(Double x, Double a, Double b, Double c) {
        return a * Math.pow(x, 2) + b * x + c;
    }

    // ------------------------------------------------------------------------------------------

    // TODO: consider writting some tests instead of using a main method
    public static void main(String[] args) {
        Serializable solution2 = calculateInAnotherWay(1.0, 1.0, 1.0, 1.0);

        Lazy<Double> solution = calculate(2.0, 2.0, 2.0, 2.0);

        log.info("Solution_1: '{}'", solution.isEvaluated());
        log.info("Solution_1: '{}'", solution.get());
        log.info("Solution_1: '{}'", solution.isEvaluated());
        log.info("Solution_1: '{}'", solution.get());

        // Casting '(Double) solution2' throws the ClassCastException, because of Proxy usage
        //log.info("Solution_2: '{}'", (Double) solution2);
        log.info("Solution_2: '{}'",
            Double.valueOf(String.valueOf(solution2))); // yep, String.valueOf should be good enough
    }
}
