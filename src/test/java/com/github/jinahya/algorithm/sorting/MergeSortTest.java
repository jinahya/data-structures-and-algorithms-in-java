package com.github.jinahya.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.github.jinahya.algorithm.sorting.MergeSort.sort;
import static com.github.jinahya.algorithm.sorting.SortingTests.usersForTestingStability;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import static java.util.Comparator.reverseOrder;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Slf4j
public class MergeSortTest {

    // ------------------------------------------------------------------------------- sortTopDown(List<? super E>, ...)
    @Test
    public void testSortTopDownListWithEmptyList() {
        final List<Integer> unsorted = emptyList();
        final Comparator<Integer> comparator = naturalOrder();
        final List<Integer> sorted = new ArrayList<>(unsorted.size());
        sort(unsorted, comparator, sorted);
        assertThat(sorted)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testSortTopDownListWithSingletonList() {
        final List<Integer> unsorted = singletonList(current().nextInt());
        final Comparator<Integer> comparator = naturalOrder();
        final List<Integer> sorted = new ArrayList<>(unsorted.size());
        sort(unsorted, comparator, sorted);
        assertThat(unsorted)
                .isSortedAccordingTo(comparator);
    }

    /**
     * Tests {@link MergeSort#sort(List, Comparator, Collection)} method with a list of random elements.
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
            sort(new ArrayList<>(unsorted), comparator, actual);
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
            sort(unsorted, nullsFirst(reverseOrder()), actual);
        }
        assertIterableEquals(asList(null, "3", "2", "1"), actual);
    }

    @Test
    public void testSortTopDownListForStability() {
        final List<SortingTests.User> unsorted = usersForTestingStability();
        log.debug("unsorted: {}", unsorted);
        final List<SortingTests.User> sorted = new ArrayList<>(unsorted.size());
        MergeSort.sortTopDown(unsorted, SortingTests.User.COMPARING_AGE, sorted);
        log.debug("sorted: {}", sorted);
        assertThat(sorted)
                .isSortedAccordingTo(SortingTests.User.COMPARING_AGE);
        final boolean stable = unsorted.indexOf(SortingTests.User.JANE) == 2;
        log.debug("stable: {}", stable);
    }
}
