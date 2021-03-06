package com.github.jinahya.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

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
            if (comparator.compare(list.get(j), list.get(p)) < 0) {
                swap(list, j, i++);
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
    private static <E> int partitionHoare(final List<E> list, final Comparator<? super E> comparator) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }
        if (list.size() < 2) {
            throw new IllegalArgumentException("list.size(" + list.size() + ") < 2");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        final E p = list.get((list.size() - 1) / 2);
        int i = -1;
        int j = list.size();
        while (true) {
            do {
                i++;
            } while (comparator.compare(list.get(i), p) < 0);
            assert comparator.compare(list.get(i), p) >= 0;
            do {
                j--;
            } while (comparator.compare(list.get(j), p) > 0);
            assert comparator.compare(list.get(j), p) <= 0;
            if (i >= j) {
                return j;
            }
            swap(list, i, j);
        }
    }

    /**
     * Sorts all elements in specified list using <a href="https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme">Hoare
     * partition schema</a>.
     *
     * @param list       the list whose elements are sorted.
     * @param comparator a comparator for comparing elements.
     * @param <E>        element type parameter
     */
    public static <E> void sortHoare(final List<E> list, final Comparator<? super E> comparator) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        if (list.size() < 2) {
            return;
        }
        final int p = partitionHoare(list, comparator);
        assert p >= 0;
        assert p < list.size() - 1;
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
