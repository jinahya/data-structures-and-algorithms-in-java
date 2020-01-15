package com.github.jinahya.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import static java.util.Collections.swap;

/**
 * A class contains Quicksort implementations.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://en.wikipedia.org/wiki/Quicksort">Quicksort (Wikipedia)</a>
 */
@Slf4j
public class Quicksort {

    // ---------------------------------------------------------------------------------------------------------- lomuto
    static final ThreadLocal<LongAdder> LOMUTO_SWAP_COUNTER = new ThreadLocal<>();

    private static <E> int partitionLomuto(final List<E> list, final Comparator<? super E> comparator) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        final int p = list.size() - 1;
        int i = 0;
        for (int j = 0; j < list.size() - 1; j++) {
            if (comparator.compare(list.get(j), list.get(p)) < 0) { // not stable
                swap(list, j, i++);
                LOMUTO_SWAP_COUNTER.get().increment();
            }
        }
        swap(list, p, i);
        return i;
    }

    /**
     * Sorts all elements in specified list using <a href="https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme">Lomuto
     * partition scheme</a>
     *
     * @param list       the list whose elements are sorted.
     * @param comparator a comparator for comparing elements.
     * @param <E>        element type parameter
     */
    public static <E> void sortLomuto(final List<E> list, final Comparator<? super E> comparator) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        if (list.size() < 2) {
            return;
        }
        final int p = partitionLomuto(list, comparator);
        assert p >= 0;
        assert p < list.size();
        sortLomuto(list.subList(0, p), comparator);
        sortLomuto(list.subList(p + 1, list.size()), comparator);
    }

    // ----------------------------------------------------------------------------------------------------------- hoare
    static final ThreadLocal<LongAdder> HOARE_SWAP_COUNTER = new ThreadLocal<>();

    static <E> int partitionHoare(final List<E> list, final Comparator<? super E> comparator) {
        assert !list.isEmpty();
        final E p = list.get(list.size() / 2);
        int i = -1;
        int j = list.size();
        while (true) {
            do {
                i++;
            } while (comparator.compare(list.get(i), p) < 0);
            do {
                j++;
            } while (comparator.compare(list.get(j), p) > 0);
            if (i >= j) {
                return j;
            }
            swap(list, i, j);
            HOARE_SWAP_COUNTER.get().increment();
        }
    }

    /**
     * Sorts all elements in specified list using <a href="https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme">Hoare
     * partition schema</a>.
     *
     * @param list       the list whose elements are sorted.
     * @param comparator the comparator for comparing elements.
     * @param <E>        element type parameter
     */
    public static <E> void sortHoare(final List<E> list, final Comparator<? super E> comparator) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        HOARE_SWAP_COUNTER.remove();
        HOARE_SWAP_COUNTER.set(new LongAdder());
        if (list.size() < 2) {
            return;
        }
        final int p = partitionHoare(list, comparator);
        sortHoare(list.subList(0, p + 1), comparator);
        sortHoare(list.subList(p + 1, list.size()), comparator);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    private Quicksort() {
        super();
    }
}
