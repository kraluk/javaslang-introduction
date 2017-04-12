package com.kraluk.workshop.javaslang.task

import spock.lang.Shared
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

class TryTaskSpec extends Specification {

    @Shared
    def content = "Some test file\nAnd another line...\n"

    @Shared
    def expectedList

    @Shared
    def pathToNothing

    @Shared
    def tempPath

    def setupSpec() {
        def temp = File.createTempFile("temp", UUID.randomUUID().toString())
        tempPath = temp.getAbsolutePath()
        Files.write(Paths.get(tempPath), content.getBytes())

        expectedList = Arrays.asList(content.split("\\n"))
        pathToNothing = System.getProperty("java.io.tmpdir") + File.separator + UUID.randomUUID().toString()
    }

    def "should handle unusual situations and recover from it"() {
        when:
            def result = TryTask.getResult(path)

        then:
            result == expected

        where:
            path                    || expected
            tempPath as String      || content.split("\\n")
            pathToNothing as String || TryTask.IO_ERROR
            null                    || TryTask.NPE_ERROR

    }

    def "should handle unusual situations and recover from it in an another way"() {
        when:
            def result = TryTask.getResultInAnotherWay(path)

        then:
            result == expected

        where:
            path                    || expected
            tempPath as String      || content.split("\\n")
            pathToNothing as String || TryTask.IO_ERROR
            null                    || TryTask.NPE_ERROR
    }

}
