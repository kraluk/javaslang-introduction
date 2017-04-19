package com.kraluk.workshop.vavr.example

import com.kraluk.workshop.vavr.common.enums.Result
import com.kraluk.workshop.vavr.common.exception.WorkshopException
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
            throwFileNotFoundException    || Result.SECOND
            throwNullPointerException     || Result.SECOND_OR_THIRD
            returnSomeValue               || Result.FORTH
    }

    @Unroll
    def "should successfully invoke TryExamples.tryWithFailureType"() {

        when:
            def result = TryExamples.tryWithFailureType(supplier)

        then:
            assert result.class == expectedClazz
            assert result == expectedValue

        where:
            supplier                  || expectedValue                    || expectedClazz
            returnSomeValue           || "Domyslny sukces!"               || String
            returnOne                 || "Sukces, ktorego nie bedzie :-(" || String
            returnDouble              || Integer.MAX_VALUE                || Integer
            throwWorkshopException    || "Lorkszopowy blad!"              || String
            throwNullPointerException || "Domyslny blad!"                 || String
    }

    // --- Helpers

    static throwIllegalArgumentException = {
        -> throw new IllegalArgumentException("Hello!")
    }

    static throwFileNotFoundException = {
        -> throw new FileNotFoundException()
    }

    static throwNullPointerException = {
        -> throw new NullPointerException()
    }

    static throwWorkshopException = {
        -> throw new WorkshopException()
    }

    static returnSomeValue = {
        -> return Result.FORTH
    }

    static returnOne = {
        -> return 1
    }

    static returnDouble = {
        -> return 666.666 as Double
    }
}
