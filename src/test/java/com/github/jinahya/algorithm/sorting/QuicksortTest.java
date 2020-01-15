package com.github.jinahya.algorithm.sorting;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import static com.github.jinahya.algorithm.sorting.Quicksort.LOMUTO_SWAP_COUNTER;
import static com.github.jinahya.algorithm.sorting.Quicksort.sortLomuto;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import static java.util.Comparator.reverseOrder;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Slf4j
public class QuicksortTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Getter
    static final class User {

        public User(final int age, final String name) {
            super();
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }

        final int age;

        final String name;
    }

    static List<User> users() {
        final List<User> users = new ArrayList<>();
        users.add(new User(3, "Three"));
        users.add(new User(2, "John"));
        users.add(new User(2, "Jane"));
        users.add(new User(1, "One"));
        return users;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testLomutoWithEmptyList() {
        final List<Integer> list = emptyList();
        final Comparator<Integer> comparator = naturalOrder();
        sortLomuto(list, comparator);
        assertThat(list)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testLomutoWithSingletonList() {
        final List<Integer> list = singletonList(current().nextInt());
        final Comparator<Integer> comparator = naturalOrder();
        sortLomuto(list, comparator);
        assertThat(list)
                .isSortedAccordingTo(comparator);
    }

    @Test
    public void testLomutoWithRandomElements() {
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
    public void testLomutoReverse() {
        final List<String> actual = new ArrayList<>();
        actual.add("3");
        actual.add("1");
        actual.add(null);
        actual.add("2");
        sortLomuto(actual, nullsFirst(reverseOrder()));
        assertIterableEquals(asList(null, "3", "2", "1"), actual);
    }

    @Test
    public void testLomutoStability() {
        final List<User> list = users();
        log.debug("unsorted: {}", list);
        final Comparator<User> comparator = comparing(User::getAge);
        LOMUTO_SWAP_COUNTER.remove();
        LOMUTO_SWAP_COUNTER.set(new LongAdder());
        sortLomuto(list, comparator);
        log.debug("sorted: {}", list);
        assertThat(list)
                .isSortedAccordingTo(comparator);
        log.debug("swaps: {}", LOMUTO_SWAP_COUNTER.get().sum());
    }
}
