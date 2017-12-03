package com.kraluk.workshop.vavr.task

import spock.lang.Specification

class LottoTaskSpec extends Specification {

    def "should get six random number"() {
        when:
        def result = LottoTask.getLottoNumbers()

        then:
        result.size() == 6
        and:
        result.unique().size() == 6
        and:
        System.out.println(Arrays.toString(result.toArray()))

        where:
        i << (1..100)
    }
}
