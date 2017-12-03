package com.kraluk.workshop.vavr.example;

import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * Validation Examples
 *
 * @author lukasz
 */
public final class ValidationExample {

    private ValidationExample() {
    }

    static class PersonValidator {

        private static final String EMPTY = "";

        private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
        private static final int MIN_AGE = 18;

        private PersonValidator() {
        }

        public static Validation<Seq<String>, Person> validatePerson(String name, int age) {
            return Validation
                .combine(validateName(name), validateAge(age))
                .ap(Person::new);
        }

        private static Validation<String, String> validateName(String name) {
            return CharSeq.of(name)
                .replaceAll(VALID_NAME_CHARS, EMPTY)
                .transform(seq -> seq.isEmpty()
                    ? Validation.valid(name)
                    : Validation.invalid(
                        "Name contains invalid characters: '" + seq.distinct().sorted() + "'"));
        }

        private static Validation<String, Integer> validateAge(int age) {
            return age < MIN_AGE
                ? Validation.invalid("Age must be greater than " + MIN_AGE)
                : Validation.valid(age);
        }
    }

    static class Person {

        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person(" + name + ", " + age + ")";
        }
    }
}
