package com.github.jinahya.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toCollection;

public class JinahyaCollectionsTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testIsSortedWithComparator() {
        final List<Integer> list
                = IntStream.range(0, current().nextInt(1, 10))
                .map(i -> current().nextInt(0, 10))
                .sorted()
                .boxed()
                .collect(toCollection(ArrayList::new));
        Assertions.assertTrue(JinahyaCollections.isSorted(list, Comparator.naturalOrder()));
    }

    @Test
    public void testIsSortedWithFlag() {
        final List<Integer> list
                = IntStream.range(0, current().nextInt(1, 10))
                .map(i -> current().nextInt(0, 10))
                .sorted()
                .boxed()
                .collect(toCollection(ArrayList::new));
        Assertions.assertTrue(JinahyaCollections.isSorted(list, true));
    }
}
