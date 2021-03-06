package com.kraluk.workshop.vavr.example

import io.vavr.control.Validation
import spock.lang.Specification

class ValidationExampleSpec extends Specification {

    def "should validate given parameters"() {
        given:
        def name = "Leszke Smieszke"
        def age = 42

        when:
        Validation<io.vavr.collection.List<String>, ValidationExample.Person> valid =
                ValidationExample.PersonValidator.validatePerson(name, age)

        then:
        valid instanceof Validation.Valid
        and:
        with(valid) {
            isValid() == true
            isInvalid() == false
            get() instanceof ValidationExample.Person
        }
    }

    def "should not validate given parameters and return error list"() {
        given:
        def name = "^#_Justynian Bimbrownik__"
        def age = -1

        when:
        Validation<io.vavr.collection.List<String>, ValidationExample.Person> invalid =
                ValidationExample.PersonValidator.validatePerson(name, age)

        then:
        invalid instanceof Validation.Invalid
        and:
        with(invalid) {
            isValid() == false
            isInvalid() == true
        }
        and:
        invalid.getError().size() == 2
        invalid.getError().forEach { e -> print(e + "\n") }
    }
}
