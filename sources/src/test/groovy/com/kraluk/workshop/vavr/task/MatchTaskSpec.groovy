package com.kraluk.workshop.vavr.task

import javaslang.control.Option
import spock.lang.Specification

class MatchTaskSpec extends Specification {

    def "should do some complex logic"() {
        when:
            def result = MatchTask.doSomeComplexLogic(additionalOptions, importanceFlag, code, value)

        then:
            result == expected

        where:
            additionalOptions || importanceFlag || code || value || expected
            "A"               || false          || 0    || 0.0   || "SO_BAD"
            "A"               || true           || 0    || 1.0   || "OK"
            "A"               || true           || 0    || 0.0   || "RTM"
            "A"               || true           || 0    || -1.0  || "OMG"
            "A"               || true           || 1    || 0.0   || "YOLO"
            "A"               || true           || 2    || 0.0   || "YOLO"
            "y"               || true           || 0    || 0.0   || "YOLO"
            "Y"               || true           || 0    || 0.0   || "YOLO"
            "b"               || true           || 0    || 0.0   || null
            null              || true           || 0    || 0.0   || null
    }

    def "should do some complex logic in a better way"() {
        when:
            def result = MatchTask.doSomeComplexLogicInBetterWay(additionalOptions, importanceFlag, code, value)

        then:
            result.get() == expected

        where:
            additionalOptions || importanceFlag || code || value || expected
            "A"               || false          || 0    || 0.0   || "SO_BAD"
            "A"               || true           || 0    || 1.0   || "OK"
            "A"               || true           || 0    || 0.0   || "RTM"
            "A"               || true           || 0    || -1.0  || "OMG"
            "A"               || true           || 1    || 0.0   || "YOLO"
            "A"               || true           || 2    || 0.0   || "YOLO"
            "y"               || true           || 0    || 0.0   || "YOLO"
            "Y"               || true           || 0    || 0.0   || "YOLO"

    }

    def "should do some complex logic in a better way (null protection)"() {
        when:
            def result = MatchTask.doSomeComplexLogicInBetterWay(additionalOptions, importanceFlag, code, value)

        then:
            result == expected

        where:
            additionalOptions || importanceFlag || code || value || expected
            "b"               || true           || 0    || 0.0   || Option.none()
            null              || true           || 0    || 0.0   || Option.none()
    }

}
