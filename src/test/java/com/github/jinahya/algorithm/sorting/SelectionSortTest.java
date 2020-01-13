package com.github.jinahya.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.github.jinahya.algorithm.sorting.SelectionSort.sort;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Comparator.*;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * A class for testing {@link SelectionSort}
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
public class SelectionSortTest {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Tests {@link SelectionSort#sort(int[], int, int)} method with an empty list.
     */
    @Test
    public void testSortWithEmptyList() {
        final List<String> list = emptyList();
        final Comparator<String> comparator = naturalOrder();
        sort(list, comparator);
        assertThat(list)
                .isSortedAccordingTo(comparator);
    }

    /**
     * Test {@link SelectionSort#sort(int[], int, int)} method with a singleton list.
     */
    @Test
    public void testSortWithSingletonList() {
        final List<Integer> list = singletonList(current().nextInt());
        final Comparator<Integer> comparator = naturalOrder();
        sort(list, comparator);
        assertThat(list)
                .isSortedAccordingTo(comparator);
    }

    /**
     * Tests {@link SelectionSort#sort(List, Comparator)} method with a list of random elements.
     */
    @Test
    public void testSort() {
        final List<Integer> unsorted
                = range(0, current().nextInt(2, 128))
                .map(i -> current().nextInt())
                .boxed()
                .collect(toList());
        final List<Integer> expected = new ArrayList<>(unsorted);
        {
            Collections.sort(expected);
        }
        final Comparator<Integer> comparator = naturalOrder();
        final List<Integer> actual = new ArrayList<>(unsorted);
        {
            sort(actual, comparator);
        }
        assertIterableEquals(expected, actual);
    }

    /**
     * Tests {@link SelectionSort#sort(List, Comparator)} method in reverse order.
     */
    @Test
    public void testSortReverse() {
        final List<String> actual = new ArrayList<>();
        actual.add("3");
        actual.add("1");
        actual.add(null);
        actual.add("2");
        sort(actual, nullsFirst(reverseOrder()));
        assertIterableEquals(asList(null, "3", "2", "1"), actual);
    }
}
