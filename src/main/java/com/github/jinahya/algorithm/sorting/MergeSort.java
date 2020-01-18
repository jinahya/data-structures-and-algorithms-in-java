package com.github.jinahya.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.copyOfRange;

@Slf4j
public class MergeSort {

    // --------------------------------------------------------------------------------------------------------- topDown

    /**
     * Sorts all elements in specified list using specified comparator and adds those sorted elements to specified
     * collection.
     *
     * @param unsorted   the list whose elements are sorted.
     * @param comparator the comparator for comparing elements.
     * @param sorted     the collection to which those sorted elements are added.
     * @param <E>        element type parameter
     */
    public static <E> void sortTopDown(final List<? extends E> unsorted, final Comparator<? super E> comparator,
                                       final Collection<? super E> sorted) {
        if (unsorted == null) {
            throw new NullPointerException("list is null");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        if (sorted == null) {
            throw new NullPointerException("sorted is null");
        }
        if (unsorted.size() < 2) {
            sorted.addAll(unsorted);
            return;
        }
        final int m = unsorted.size() / 2;
        final List<E> l = new ArrayList<>();
        sortTopDown(unsorted.subList(0, m), comparator, l);
        final List<E> r = new ArrayList<>();
        sortTopDown(unsorted.subList(m, unsorted.size()), comparator, r);
        for (int i = 0, j = 0; i < l.size() || j < r.size(); ) {
            if (i == l.size()) {
                sorted.add(r.get(j++));
                continue;
            }
            if (j == r.size()) {
                sorted.add(l.get(i++));
                continue;
            }
            if (comparator.compare(l.get(i), r.get(j)) <= 0) {
                sorted.add(l.get(i++));
            } else {
                sorted.add(r.get(j++));
            }
        }
    }

    public static <T> T[] sortTopDown(final T[] unsorted, final Comparator<? super T> comparator) {
        if (unsorted == null) {
            throw new NullPointerException("list is null");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        if (unsorted.length < 2) {
            return copyOf(unsorted, unsorted.length);
        }
        final T[] sorted = copyOf(unsorted, unsorted.length);
        final int m = unsorted.length / 2;
        final T[] l = sortTopDown(copyOfRange(unsorted, 0, m), comparator);
        final T[] r = sortTopDown(copyOfRange(unsorted, m, unsorted.length), comparator);
        for (int i = 0, j = 0, k = 0; i < l.length || j < r.length; ) {
            if (i == l.length) {
                sorted[k++] = r[j++];
                continue;
            }
            if (j == r.length) {
                sorted[k++] = l[i++];
                continue;
            }
            if (comparator.compare(l[i], r[j]) <= 0) {
                sorted[k++] = l[i++];
            } else {
                sorted[k++] = r[j++];
            }
        }
        return sorted;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    private MergeSort() {
        super();
    }
}
