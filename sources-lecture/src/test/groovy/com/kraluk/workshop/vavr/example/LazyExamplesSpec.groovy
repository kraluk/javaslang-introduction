package com.kraluk.workshop.vavr.example

import spock.lang.Specification

class LazyExamplesSpec extends Specification {

    def "should test *of* Lazy"() {
        when:
        def result = LazyExamples.ofLazy()

        then:
        result < 1
    }

    def "should test *val* Lazy"() {
        when:
        LazyExamples.valLazy()

        then:
        notThrown Exception
    }
}