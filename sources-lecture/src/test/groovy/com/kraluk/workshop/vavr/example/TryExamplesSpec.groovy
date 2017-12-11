package com.kraluk.workshop.vavr.example

import com.kraluk.workshop.vavr.common.enums.Result
import spock.lang.Specification
import spock.lang.Unroll

class TryExamplesSpec extends Specification {

    @Unroll
    def "should invoke TryExamples.tryWithResult"() {

        when:
        def result = TryExamples.tryWithResult(supplier)

        then:
        result == expected

        where:
        supplier                      || expected
        throwIllegalArgumentException || Result.FIRST
        throwArithmeticException      || Result.SECOND
        throwNullPointerException     || Result.SECOND_OR_THIRD
        returnSomeValue               || Result.FORTH
    }

    // --- Helpers

    static throwIllegalArgumentException = {
        -> throw new IllegalArgumentException("Hello!")
    }

    static throwArithmeticException = {
        -> throw new ArithmeticException("Not permitted!")
    }

    static throwNullPointerException = {
        -> throw new NullPointerException()
    }

    static returnSomeValue = {
        -> return Result.FORTH
    }
}
