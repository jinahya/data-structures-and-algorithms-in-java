package com.github.jinahya.algorithm.sort;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toCollection;

public final class SortTests {

    // -----------------------------------------------------------------------------------------------------------------
    public static Stream<Arguments> argumentsOfArrayListsWithRandomInts() {
        return IntStream.range(0, current().nextInt(1, 10))
                .mapToObj(i -> IntStream.range(0, current().nextInt(1, 10))
                        .map(j -> current().nextInt(0, 10))
                        .boxed()
                        .collect(toCollection(ArrayList::new)))
                .map(Arguments::of);
    }

    public static Stream<Arguments> argumentsOfLinkedListsWithRandomInts() {
        return IntStream.range(0, current().nextInt(1, 10))
                .mapToObj(i -> IntStream.range(0, current().nextInt(1, 10))
                        .map(j -> current().nextInt(0, 10))
                        .boxed()
                        .collect(toCollection(LinkedList::new)))
                .map(Arguments::of);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private SortTests() {
        super();
    }
}
