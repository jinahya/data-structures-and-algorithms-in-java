package com.github.jinahya.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.github.jinahya.algorithm.sorting.MergeSort.sortTopDown;
import static com.github.jinahya.algorithm.sorting.SortingTests.usersForTestingStability;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Comparator.*;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Slf4j
public class MergeSortTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testSortTopDownListWithEmptyList() {
        final List<Integer> unsorted = emptyList();
        final Comparator<Integer> comparator = naturalOrder();
        final List<Integer> sorted = new ArrayList<>(unsorted.size());
        sortTopDown(unsorted, comparator, sorted);
        assertThat(sorted)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testSortTopDownListWithSingletonList() {
        final List<Integer> unsorted = singletonList(current().nextInt());
        final Comparator<Integer> comparator = naturalOrder();
        final List<Integer> sorted = new ArrayList<>(unsorted.size());
        sortTopDown(unsorted, comparator, sorted);
        assertThat(unsorted)
                .isSortedAccordingTo(comparator);
    }

    /**
     * Tests {@link MergeSort#sortTopDown(List, Comparator, Collection)} method with a list of random elements.
     */
    @Test
    public void testSortTopDownListWithRandomElements() {
        final List<Integer> unsorted
                = range(0, current().nextInt(2, 128))
                .map(i -> current().nextInt())
                .boxed()
                .collect(toList());
        final List<Integer> expected = new ArrayList<>(unsorted);
        {
            Collections.sort(expected);
            assertThat(expected)
                    .isSortedAccordingTo(naturalOrder());
        }
        final Comparator<Integer> comparator = naturalOrder();
        final List<Integer> actual = new ArrayList<>(unsorted.size());
        {
            sortTopDown(new ArrayList<>(unsorted), comparator, actual);
            assertThat(actual)
                    .isSortedAccordingTo(comparator);
        }
        assertIterableEquals(expected, actual);
    }

    @Test
    public void testSortTopDownListNullFirstReverseOrder() {
        final List<String> actual = new ArrayList<>();
        {
            final List<String> unsorted = new ArrayList<>();
            unsorted.add("3");
            unsorted.add("1");
            unsorted.add(null);
            unsorted.add("2");
            sortTopDown(unsorted, nullsFirst(reverseOrder()), actual);
        }
        assertIterableEquals(asList(null, "3", "2", "1"), actual);
    }

    @Test
    public void testSortTopDownListForStability() {
        final List<SortingTests.User> unsorted = usersForTestingStability();
        log.debug("unsorted: {}", unsorted);
        final List<SortingTests.User> sorted = new ArrayList<>(unsorted.size());
        sortTopDown(unsorted, SortingTests.User.COMPARING_AGE, sorted);
        log.debug("sorted: {}", sorted);
        assertThat(sorted)
                .isSortedAccordingTo(SortingTests.User.COMPARING_AGE);
        final boolean stable = unsorted.indexOf(SortingTests.User.JANE) == 2;
        log.debug("stable: {}", stable);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testSortTopDownArrayWithEmptyArray() {
        final Integer[] unsorted = new Integer[0];
        final Comparator<Integer> comparator = naturalOrder();
        final Integer[] sorted = sortTopDown(unsorted, comparator);
        assertThat(sorted)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testSortTopDownArrayWithSingletonArray() {
        final Integer[] unsorted = new Integer[]{current().nextInt()};
        final Comparator<Integer> comparator = naturalOrder();
        final Integer[] sorted = sortTopDown(unsorted, comparator);
        assertThat(unsorted)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testSortTopDownArrayWithRandomElements() {
        final Integer[] unsorted = range(2, current().nextInt(128))
                .map(i -> current().nextInt())
                .boxed()
                .toArray(Integer[]::new);
        final Comparator<Integer> comparator = naturalOrder();
        final Integer[] expected = Arrays.copyOf(unsorted, unsorted.length);
        Arrays.sort(expected, comparator);
        final Integer[] actual = sortTopDown(unsorted, comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortTopDownArrayNullsFirstReverseOrder() {
        final String[] unsorted = new String[]{"3", "1", null, "2"};
        final String[] sorted = sortTopDown(unsorted, nullsFirst(reverseOrder()));
        assertArrayEquals(new String[]{null, "3", "2", "1"}, sorted);
    }
}
