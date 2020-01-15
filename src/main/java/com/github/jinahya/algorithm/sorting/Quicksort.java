package com.github.jinahya.algorithm.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.Collections.swap;

public class Quicksort {

    // -----------------------------------------------------------------------------------------------------------------
    public static <E> void sort(final List<E> list, final Comparator<? super E> comparator,
                                final BiFunction<? super List<E>, ? super Comparator<? super E>, Integer> partitioner) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        if (partitioner == null) {
            throw new NullPointerException("partitioner is null");
        }
        if (list.size() < 2) {
            return;
        }
        final int p = partitioner.apply(list, comparator);
        assert p >= 0;
        assert p < list.size();
        sort(list.subList(0, p), comparator, partitioner);
        sort(list.subList(p + 1, list.size()), comparator, partitioner);
    }

    public static <E> void lomuto(final List<E> list, final Comparator<? super E> comparator) {
        sort(list,
             comparator,
             (l, c) -> {
                 assert !l.isEmpty();
                 final int p = l.size() - 1;
                 int i = 0; // the index to be swapped with the pivot
                 for (int j = 0; j < l.size() - 1; j++) {
                     if (c.compare(l.get(j), l.get(p)) <= 0) {
                         swap(l, j, i++);
                     }
                 }
                 swap(l, p, i);
                 return i;
             });
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    private Quicksort() {
        super();
    }
}
