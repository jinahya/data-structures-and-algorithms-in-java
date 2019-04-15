package com.github.jinahya.util;

import java.util.Collection;
import java.util.Comparator;

public final class JinahyaCollections {

    // -----------------------------------------------------------------------------------------------------------------
    public static <E> boolean isSorted(final Collection<? extends E> collection,
                                       final Comparator<? super E> comparator) {
        E previous = null;
        for (final E next : collection) {
            if (previous != null && comparator.compare(previous, next) > 0) {
                return false;
            }
            previous = next;
        }
        return true;
    }

    public static <E extends Comparable<? super E>> boolean isSorted(final Collection<? extends E> collection,
                                                                     final boolean natural) {
        return isSorted(collection, natural ? Comparator.naturalOrder() : Comparator.reverseOrder());
    }

    public static <E extends Comparable<? super E>> boolean isSorted(final Collection<? extends E> collection) {
        return isSorted(collection, true);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JinahyaCollections() {
        super();
    }
}
