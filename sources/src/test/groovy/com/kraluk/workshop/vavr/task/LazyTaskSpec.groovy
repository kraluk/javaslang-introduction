package com.kraluk.workshop.vavr.task

import spock.lang.Specification

class LazyTaskSpec extends Specification {

    def "should calculate some complex operation in a Lazy way (OF)"() {
        when:
        def result = LazyTask.calculate(1, 1, 1, 1)

        then:
        !result.isEvaluated()
        result.get() == 3.0D
        result.isEvaluated()
    }

    def "should calculate some complex operation in a Lazy way (VAL)"() {
        when:
        def result = LazyTask.calculateInAnotherWay(1, 1, 1, 1)

        then:
        result instanceof Serializable
        String.valueOf(result) == "3.0"
    }

    def "should calculate some complex operation in a Lazy alternative way (VAL)"() {
        when:
        def result = LazyTask.calculateInAlternativeWay(1, 1, 1, 1)

        then:
        result instanceof Serializable
        String.valueOf(result) == "3.0"
    }
}