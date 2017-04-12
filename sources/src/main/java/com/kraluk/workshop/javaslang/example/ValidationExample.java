package com.kraluk.workshop.javaslang.example;

import javaslang.collection.CharSeq;
import javaslang.collection.List;
import javaslang.control.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Validation Examples
 *
 * @author lukasz
 */
public class ValidationExample {
    private static final Logger log = LoggerFactory.getLogger(ValidationExample.class);

    private ValidationExample() {
    }

    public static void checkValidPerson() {
        PersonValidator personValidator = new PersonValidator();

        Validation<List<String>, Person>
                valid =
                personValidator.validatePerson("Leszke Smieszke", 42);
        log.info("{}", valid.get());
    }

    public static void checkInvalidPerson() {
        PersonValidator personValidator = new PersonValidator();

        Validation<List<String>, Person>
                invalid =
                personValidator.validatePerson("Justynian Bimber", -1);
        //log.info("{}", invalid.get());
        log.info("{}", Arrays.toString(invalid.getError().toJavaArray()));
    }

    public static void main(String[] args) {
        checkValidPerson();
        checkInvalidPerson();
    }
}

class PersonValidator {

    private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
    private static final int MIN_AGE = 18;

    public Validation<List<String>, Person> validatePerson(String name, int age) {
        return Validation.combine(validateName(name), validateAge(age)).ap(Person::new);
    }

    private Validation<String, String> validateName(String name) {
        return CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "").transform(seq -> seq.isEmpty()
                ? Validation.valid(name)
                : Validation.invalid("Name contains invalid characters: '"
                + seq.distinct().sorted() + "'"));
    }

    private Validation<String, Integer> validateAge(int age) {
        return age < MIN_AGE
                ? Validation.invalid("Age must be greater than " + MIN_AGE)
                : Validation.valid(age);
    }
}

class Person {

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
