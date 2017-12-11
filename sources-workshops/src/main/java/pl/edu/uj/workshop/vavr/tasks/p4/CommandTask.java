package pl.edu.uj.workshop.vavr.tasks.p4;

import io.vavr.Lazy;

public final class CommandTask {

    private CommandTask() {
    }

    /*
     * Represents a lazy evaluated value. Compared to a Supplier, Lazy is memoizing, i.e. it evaluates only once and
     * therefore is referential transparent.
     */
    public static Lazy<Double> getCommand() {
        return Lazy.of(CommandTask::calculateHeavyOperations);
    }

    private static double calculateHeavyOperations() {
        double result = 1;

        for (int i = 1; i < 1_000_000_000; i++) {
            result += i * 1.0;
        }

        return result;
    }
}
