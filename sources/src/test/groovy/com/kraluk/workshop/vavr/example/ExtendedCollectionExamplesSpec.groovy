package com.kraluk.workshop.vavr.example

import spock.lang.Specification

class ExtendedCollectionExamplesSpec extends Specification {

    def "should filter integer values"() {
        when:
            def result = ExtendedCollectionExamples.filterValues(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        then:
            result == [2, 4, 6, 8, 10]
    }

    def "should get some doubles"() {
        when:
            def result = ExtendedCollectionExamples.someRandomDoubles

        then:
            result.each { e -> assert e > 0.5 && e < 0.6 }
    }
}
