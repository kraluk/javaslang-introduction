package pl.edu.uj.workshop.vavr.tasks.p1;

import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Validation;

public final class DistinctionValidatorTask {

    private static final String EMPTY = "";
    private static final String VALID_NAME_CHARS = "[a-zA-Z ]";

    private DistinctionValidatorTask() {
    }

    public static Validation<Seq<String>, ProposedDistinction> validateDistinction(
        final String name,
        final Integer currentYear,
        final Seq<Double> grades,
        final Seq<String> reprimands) {

        return Validation.combine(
            validateName(name),
            validateYear(currentYear),
            validateGrades(grades),
            validateReprimands(reprimands))
            .ap(ProposedDistinction::new);
    }

    private static Validation<String, String> validateName(final String name) {
        return CharSeq.of(name)
            .replaceAll(VALID_NAME_CHARS, EMPTY)
            .transform(seq -> seq.isEmpty()
                ? Validation.valid(name)
                : Validation.invalid(
                    "Name contains invalid characters: '" + seq.distinct().sorted() + "'"));
    }

    private static Validation<String, Integer> validateYear(final Integer year) {
        return Option.of(year)
            .filter(y -> y >= 3)
            .filter(y -> y <= 5)
            .transform(o -> o.isDefined()
                ? Validation.valid(year)
                : Validation.invalid("The given year '" + year + "' is not allowed!"));
    }

    private static Validation<String, Double> validateGrades(final Seq<Double> grades) {
        return grades.average()
            .filter(v -> v >= 4.75)
            .transform(o -> o.isDefined()
                ? Validation.valid(o.get())
                : Validation.invalid("Given average grade is too low!"));
    }

    private static Validation<String, Integer> validateReprimands(final Seq<String> reprimands) {
        return reprimands.size() == 0
            ? Validation.valid(reprimands.size())
            : Validation.invalid("Student with any reprimand cannot be distincted!");
    }
}
