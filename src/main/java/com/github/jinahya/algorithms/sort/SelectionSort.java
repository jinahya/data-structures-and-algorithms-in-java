package com.github.jinahya.algorithms.sort;

import java.util.Comparator;
import java.util.List;

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

    // -----------------------------------------------------------------------------------------------------------------
    private SelectionSort() {
        super();
    }
}
