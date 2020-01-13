package com.github.jinahya.algorithm.sorting;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class MergeSort {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Sorts all elements in specified list using specified comparator and adds those sorted elements to specified
     * collection.
     *
     * @param unsorted   the list whose elements are sorted.
     * @param comparator the comparator for comparing elements.
     * @param sorted     the collection to which those sorted elements are added.
     * @param <E>        element type parameter
     */
    public static <E> void sort(final List<? extends E> unsorted, final Comparator<? super E> comparator,
                                final Collection<? super E> sorted) {
        topDown(unsorted, comparator, sorted);
    }

    // top-down
    private static <E> void topDown(final List<? extends E> unsorted, final Comparator<? super E> comparator,
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
        final List<E> l = new ArrayList<>(); // 전체 값들 중 왼쪽 반만큼이 정렬되서 추가될 리스트.
        topDown(unsorted.subList(0, m), comparator, l);
        final List<E> r = new ArrayList<>(); // 전체 값들 중 오른쪽 반만큼이 정렬되서 추가되 리스트.
        topDown(unsorted.subList(m, unsorted.size()), comparator, r);
        for (int i = 0, j = 0; i < l.size() || j < r.size(); ) {
            if (i == l.size()) {        // 왼쪽 그룹에 있는 모든 값들이 이미 사용되었습니다.
                sorted.add(r.get(j++)); // 오른쪽 그룹에 있는 값을 사용합니다.
                continue;
            }
            if (j == r.size()) {        // 오른쪽 그룹에 있는 모든 값들이 이미 사용되었습니다.
                sorted.add(l.get(i++)); // 왼쭉 그룹에 있는 값을 사용합니다.
                continue;
            }
            if (comparator.compare(l.get(i), r.get(j)) <= 0) { // 두 그룹의 현재 값들을 비교해서 더 작은 값들을 추가합니다.
                sorted.add(l.get(i++)); // 왼쪽 그룹에 있는 값이 더 작으면 그 값을 사용합니다.
            } else {
                sorted.add(r.get(j++)); // 오른쪽 그룹에 있는 값이 더 작으면 그 값을 사용합니다.
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private MergeSort() {
        super();
    }
}
