package com.github.jinahya.algorithms.sort;

import com.github.jinahya.util.JinahyaCollections;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class InsertionSortTest {

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"com.github.jinahya.algorithms.sort.SortTests#argumentsOfArrayListsWithRandomInts",
                   "com.github.jinahya.algorithms.sort.SortTests#argumentsOfLinkedListsWithRandomInts"})
    @ParameterizedTest
    public void testSort1(final List<Integer> list) {
        log.debug("unsorted: {}", list);
        final Comparator<Integer> comparator = Comparator.naturalOrder();
        InsertionSort.sort1(list, comparator);
        log.debug("sorted: {}", list);
        assertTrue(JinahyaCollections.isSorted(list, comparator));
    }
}
