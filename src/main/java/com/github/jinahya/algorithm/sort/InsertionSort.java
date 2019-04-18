package com.github.jinahya.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public final class InsertionSort {

    // -----------------------------------------------------------------------------------------------------------------
    public static <E> List<E> sort1(final List<E> list, final Comparator<? super E> comparator) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(list.get(j - 1), list.get(j)) > 0) {
                    final E element = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, element);
                }
            }
        }
        return list;
    }

    public static <E> List<E> sort2(final List<E> list, final Comparator<? super E> comparator) {
        if (list.isEmpty()) {
            return list;
        }
        for (final ListIterator<E> i = list.listIterator(1); i.hasNext(); ) {
            final int nextIndex = i.nextIndex();
            final E next = i.next();
            for (final ListIterator<E> j = list.listIterator(nextIndex); j.hasPrevious(); ) {
                final int previousIndex = j.previousIndex();
                final E previous = j.previous();
                if (comparator.compare(previous, next) > 0) {
                    i.remove();
                    j.add(next);
//                    j.setAt(next);
//                    i.setAt(previous);
                }
            }
        }
        return list;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private InsertionSort() {
        super();
    }
}
