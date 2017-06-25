package com.kraluk.workshop.vavr.task;

import java.util.List;
import java.util.Random;

import io.vavr.collection.Stream;

/**
 * Proposed solution of the Task 1
 *
 * @author lukasz
 */
public class LottoTask {
    private static final Random RANDOM = new Random();

    public static List<Integer> getLottoNumbers() {
        return Stream.continually(LottoTask::random)
            .take(100)
            .distinct()
            .takeRight(6)
            .sorted()
            .toJavaList();
    }

    private static Integer random() {
        return RANDOM.nextInt(41) + 1;
    }
}
