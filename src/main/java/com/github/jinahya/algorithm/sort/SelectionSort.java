package com.github.jinahya.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public final class SelectionSort {

    // -----------------------------------------------------------------------------------------------------------------
    public static <E> List<E> sort1(final List<E> list, final Comparator<? super E> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            int j = i;
            for (int k = i + 1; k < list.size(); k++) {
                if (comparator.compare(list.get(k), list.get(j)) < 0) {
                    j = k;
                }
            }
            if (j != i) {
                final E t = list.get(i);
                list.set(i, list.get(j));
                list.set(j, t);
            }
        }
        return list;
    }

    public static <E> List<E> sort2(final List<E> list, final Comparator<? super E> comparator) {
        for (final ListIterator<E> outer = list.listIterator(0); outer.hasNext(); ) {
            final int i = outer.nextIndex();
            final E n = outer.next();
            int j = i;
            for (final ListIterator<E> inner = list.listIterator(j + 1); inner.hasNext(); ) {
                final int k = inner.nextIndex();
                if (comparator.compare(inner.next(), list.get(j)) < 0) {
                    j = k;
                }
            }
            if (j != i) {
                outer.set(list.get(j));
                list.set(j, n);
            }
        }
        return list;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private SelectionSort() {
        super();
    }
}
