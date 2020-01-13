package com.github.jinahya.algorithm.sorting;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.swap;

public class SelectionSort {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Sorts elements in specified list using specified comparator.
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
        for (int i = 0; i < list.size() - 1; i++) {
            int m = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(j), list.get(m)) < 0) {
                    m = j;
                }
            }
            if (m > i) {
                swap(list, i, m);
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
        for (int i = fromIndex; i < toIndex - 1; i++) {
            int m = i;
            for (int j = i + 1; j < toIndex; j++) {
                if (comparator.compare(array[j], array[m]) < 0) {
                    m = j;
                }
            }
            if (m > i) {
                final T t = array[i];
                array[i] = array[m];
                array[m] = t;
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static void sort(final byte[] array, final int fromIndex, final int toIndex) {
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
        for (int i = fromIndex; i < toIndex - 1; i++) {
            int m = i;
            for (int j = i + 1; j < toIndex; j++) {
                if (array[j] < array[m]) {
                    m = j;
                }
            }
            if (m > i) {
                final byte t = array[i];
                array[i] = array[m];
                array[m] = t;
            }
        }
    }

    public static void sort(final short[] array, final int fromIndex, final int toIndex) {
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
        // TODO: 1/11/2020 Implement!
    }

    public static void sort(final int[] array, final int fromIndex, final int toIndex) {
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
        // TODO: 1/11/2020 Implement!
    }

    public static void sort(final long[] array, final int fromIndex, final int toIndex) {
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
        // TODO: 1/11/2020 Implement!
    }

    public static void sort(final float[] array, final int fromIndex, final int toIndex) {
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
        // TODO: 1/11/2020 Implement!
    }

    public static void sort(final double[] array, final int fromIndex, final int toIndex) {
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
        // TODO: 1/11/2020 Implement!
    }

    public static void sort(final char[] array, final int fromIndex, final int toIndex) {
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
        // TODO: 1/11/2020 Implement!
    }

    // -----------------------------------------------------------------------------------------------------------------
    private SelectionSort() {
        super();
    }
}
