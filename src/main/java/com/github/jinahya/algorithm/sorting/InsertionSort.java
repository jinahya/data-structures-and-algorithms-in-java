package com.github.jinahya.algorithm.sorting;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.swap;

public class InsertionSort {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Sorts elements in specified list, in place, using specified comparator.
     *
     * @param list       the list whose elements are sorted.
     * @param comparator the comparator for comparing elements in the {@code list}.
     * @param <E>        element type parameter
     * @see List#subList(int, int)
     */
    public static <E> void sort(final List<E> list, final Comparator<? super E> comparator) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(list.get(j - 1), list.get(j)) <= 0) {
                    break;
                }
                swap(list, j - 1, j);
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> void sort(final T[] array, final int fromIndex, final int toIndex,
                                final Comparator<? super T> comparator) {
        if (array == null) {
            throw new NullPointerException("array is null");
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException("fromIndex(" + fromIndex + ") < " + 0);
        }
        if (toIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException("toIndex(" + toIndex + ") > array.length(" + array.length + ")");
        }
        if (comparator == null) {
            throw new NullPointerException("comparator is null");
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(array[j - 1], array[j]) > 0) {
                    final T t = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = t;
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static void sort(final int[] array, final int fromIndex, final int toIndex) {
        // TODO: 1/11/2020 Implement!!!
    }

    // -----------------------------------------------------------------------------------------------------------------
    private InsertionSort() {
        super();
    }
}
