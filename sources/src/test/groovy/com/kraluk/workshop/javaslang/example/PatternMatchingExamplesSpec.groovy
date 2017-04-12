package com.kraluk.workshop.javaslang.example

import javaslang.MatchError
import javaslang.control.Option
import spock.lang.Specification

import java.time.Instant

import static com.kraluk.workshop.javaslang.core.enums.Result.*

class PatternMatchingExamplesSpec extends Specification {

    def "should do a simple matching"() {
        when:
            def result = PatternMatchingExamples.simpleMatching(index)

        then:
            result == expected

        where:
            index             || expected
            1                 || FIRST
            2                 || SECOND
            3                 || NAN
            Integer.MIN_VALUE || NAN
            Integer.MAX_VALUE || NAN
    }

    def "should throw an exception when invoking Match without \$()"() {
        when:
            PatternMatchingExamples.simpleMatchingWithoutDefault(5)

        then:
            thrown MatchError
    }

    def "should do a simple matching with JavaSlang's Option"() {
        when:
            def result = PatternMatchingExamples.simpleMatchingWithOption(index)

        then:
            result.get() == expected

        where:
            index || expected
            1     || FIRST
            2     || SECOND
    }

    def "should do a simple matching with JavaSlang's Option when unexpected index is given"() {
        when:
            def result = PatternMatchingExamples.simpleMatchingWithOption(index as int)

        then:
            result == expected

        where:
            index             || expected
            Integer.MIN_VALUE || Option.none()
            Integer.MAX_VALUE || Option.none()
    }

    def "should do a simple matching with a predicate"() {
        when:
            def result = PatternMatchingExamples.simpleMatchingWithPredicate(index)

        then:
            result == expected

        where:
            index || expected
            1     || FIRST
            2     || SECOND
            3     || NAN
    }

    def "should do a simple matching with a complex predicate"() {
        when:
            def result = PatternMatchingExamples.simpleMatchingWithComplexPredicate(index)

        then:
            result == expected

        where:
            index         || expected
            1             || FIRST
            2             || SECOND_OR_THIRD
            3             || SECOND_OR_THIRD
            4             || FORTH
            99            || FORTH
            "YOLO"        || YOLO
            Instant.now() || NAN
    }

    def "should do a simple matching with a value"() {
        when:
            def result = PatternMatchingExamples.simpleMatchingWithValue(index)

        then:
            result == expected

        where:
            index || expected
            1     || SECOND_OR_THIRD
            2     || SECOND
            4     || NAN
    }

    def "should do a simple matching with some work"() {
        given:
            def outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));

        when:
            PatternMatchingExamples.simpleMatchingWithWork(index)

        then:
            outStream.toString() == expected

        where:
            index || expected
            1     || "1\n"
            2     || "2\n"
            4     || ""
            42    || "Hello from run()!\n"
    }

}