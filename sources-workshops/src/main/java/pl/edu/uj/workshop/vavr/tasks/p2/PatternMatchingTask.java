package pl.edu.uj.workshop.vavr.tasks.p2;

import java.io.Serializable;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static pl.edu.uj.workshop.vavr.tasks.p2.OperationType.ADDITION;
import static pl.edu.uj.workshop.vavr.tasks.p2.OperationType.DIVISION;
import static pl.edu.uj.workshop.vavr.tasks.p2.OperationType.MULTIPLICATION;
import static pl.edu.uj.workshop.vavr.tasks.p2.OperationType.SUBTRACTION;

public final class PatternMatchingTask {

    private PatternMatchingTask() {
    }

    public static Number calculate(final boolean isImportant,
                                   final OperationType operationType,
                                   final double value,
                                   final Serializable number) {
        if (number instanceof Integer) {
            if (value > 0) {

                switch (operationType) {
                    case ADDITION:
                        return (Integer) number + (Integer) number;

                    case MULTIPLICATION:
                        return (Integer) number * (Integer) number;

                    default:
                        return 42;
                }
            } else {
                throw new UnsupportedOperationException("Unsupported operation!");
            }
        } else if (number instanceof Double) {
            if (isImportant) {
                if (SUBTRACTION == operationType) {
                    return (Double) number - value;
                } else if (DIVISION == operationType) {
                    return (Double) number / value;
                } else {
                    return (Double) number + (Double) number;
                }
            } else {
                throw new IllegalArgumentException("Illegal set of arguments!");
            }
        } else if (number instanceof Number) {
            return 128.821;
        } else {
            return null;
        }
    }

    public static Number calculateInABetterWay(final boolean isImportant,
                                               final OperationType operationType,
                                               final double value,
                                               final Serializable number) {
        return Match(number).of(
            Case($(instanceOf(Integer.class)), intValue -> Match(intValue).of(
                Case($(i -> value > 0), i -> Match(operationType).of(
                    Case($(ADDITION), type -> intValue + intValue),
                    Case($(MULTIPLICATION), type -> intValue * intValue),
                    Case($(), 42)
                )),

                Case($(), PatternMatchingTask::throwUoe)
            )),

            Case($(instanceOf(Double.class)), doubleValue -> Match(isImportant).of(
                Case($(true), b -> Match(operationType).of(
                    Case($(SUBTRACTION), type -> doubleValue - value),
                    Case($(DIVISION), type -> doubleValue / value),
                    Case($(), type -> doubleValue + doubleValue)
                )),

                Case($(false), PatternMatchingTask::throwIae)
            )),

            Case($(instanceOf(Number.class)), 128.821),

            Case($(), (Number) null)
        );
    }

    @SuppressWarnings("unused")
    private static Integer throwUoe(Integer integerValue) {
        throw new UnsupportedOperationException("Unsupported operation!");
    }

    @SuppressWarnings("unused")
    private static Integer throwIae(boolean boolValue) {
        throw new IllegalArgumentException("Unsupported operation!");
    }
}
