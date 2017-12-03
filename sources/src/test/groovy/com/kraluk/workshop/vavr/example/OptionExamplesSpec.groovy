package com.kraluk.workshop.vavr.example

import com.kraluk.workshop.vavr.common.exception.WorkshopException
import spock.lang.Specification

class OptionExamplesSpec extends Specification {

    def "should return given value"() {
        given:
        Integer value = 5

        when:
        def result = OptionExamples.useOption(value)

        then:
        result == 5
    }

    def "should throw an exception"() {
        given:
        Integer value = null

        when:
        OptionExamples.useOption(value)

        then:
        thrown(WorkshopException)
    }
}
