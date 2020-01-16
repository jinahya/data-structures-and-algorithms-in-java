package com.github.jinahya.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.github.jinahya.algorithm.sorting.Quicksort.sortHoare;
import static com.github.jinahya.algorithm.sorting.Quicksort.sortLomuto;
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

/**
 * A class for testing {@link Quicksort} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
public class QuicksortTest {

    // ------------------------------------------------------------------------------------------------- lomuto(List<E>)

    /**
     * Tests {@link Quicksort#sortLomuto(List, Comparator)} method with an empty list.
     */
    @Test
    public void testSortListLomutoWithEmptyList() {
        final List<Integer> list = emptyList();
        final Comparator<Integer> comparator = naturalOrder();
        sortLomuto(list, comparator);
        assertThat(list)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testSortListLomutoWithSingletonList() {
        final List<Integer> list = singletonList(current().nextInt());
        final Comparator<Integer> comparator = naturalOrder();
        sortLomuto(list, comparator);
        assertThat(list)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testSortListLomutoWithRandomElements() {
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
        final List<Integer> actual = new ArrayList<>(unsorted);
        {
            sortLomuto(actual, comparator);
            assertThat(actual)
                    .isSortedAccordingTo(comparator);
        }
        assertIterableEquals(expected, actual);
    }

    @Test
    public void testSortListLomutoNullFirstReverseOrder() {
        final List<String> actual = new ArrayList<>();
        actual.add("3");
        actual.add("1");
        actual.add(null);
        actual.add("2");
        sortLomuto(actual, nullsFirst(reverseOrder()));
        assertIterableEquals(asList(null, "3", "2", "1"), actual);
    }

    @Test
    public void testSortListLomutoForStability() {
        final List<SortingTests.User> list = usersForTestingStability();
        log.debug("unsorted: {}", list);
        sortLomuto(list, SortingTests.User.COMPARING_AGE);
        log.debug("sorted: {}", list);
        assertThat(list)
                .isSortedAccordingTo(SortingTests.User.COMPARING_AGE);
        final boolean stable = list.indexOf(SortingTests.User.JANE) == 2;
        log.debug("stable: {}", stable);
    }

    // ------------------------------------------------------------------------------------------------------- sortHoare
    @Test
    public void testSortListHoareWithEmptyList() {
        final List<Integer> list = emptyList();
        final Comparator<Integer> comparator = naturalOrder();
        sortHoare(list, comparator);
        assertThat(list)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testSortListHoareWithSingletonList() {
        final List<Integer> list = singletonList(current().nextInt());
        final Comparator<Integer> comparator = naturalOrder();
        sortHoare(list, comparator);
        assertThat(list)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testSortListHoareWithRandomElements() {
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
        final List<Integer> actual = new ArrayList<>(unsorted);
        {
            sortHoare(actual, comparator);
            assertThat(actual)
                    .isSortedAccordingTo(comparator);
        }
        assertIterableEquals(expected, actual);
    }

    @Test
    public void testSortListHoareNullFirstReverseOrder() {
        final List<String> actual = new ArrayList<>();
        actual.add("3");
        actual.add("1");
        actual.add(null);
        actual.add("2");
        sortHoare(actual, nullsFirst(reverseOrder()));
        assertIterableEquals(asList(null, "3", "2", "1"), actual);
    }

    @Test
    public void testSortListHoareForStability() {
        final List<SortingTests.User> list = usersForTestingStability();
        log.debug("unsorted: {}", list);
        sortHoare(list, SortingTests.User.COMPARING_AGE);
        log.debug("sorted: {}", list);
        assertThat(list)
                .isSortedAccordingTo(SortingTests.User.COMPARING_AGE);
        final boolean stable = list.indexOf(SortingTests.User.JANE) == 2;
        log.debug("stable: {}", stable);
    }
}
