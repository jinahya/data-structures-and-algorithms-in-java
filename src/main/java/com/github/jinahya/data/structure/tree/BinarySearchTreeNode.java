package com.github.jinahya.data.structure.tree;

import java.util.Comparator;
import java.util.Optional;

public class BinarySearchTreeNode<E> extends BinaryTreeNode<E, BinarySearchTreeNode<E>> {

    // -----------------------------------------------------------------------------------------------------------------
    BinarySearchTreeNode(final E value, final BinarySearchTreeNode<E> parent) {
        super(value, parent);
    }

    // -----------------------------------------------------------------------------------------------------------------
    Optional<BinarySearchTreeNode<E>> search(final Comparator<E> comparator, final E value) {
        for (BinarySearchTreeNode<E> cursor = this; cursor != null; ) {
            if (cursor.getValue().equals(value)) {
                return Optional.of(cursor);
            }
            if (isValueLessThan(comparator, value)) {
                cursor = cursor.getRight();
            } else {
                cursor = cursor.getLeft();
            }
        }
        return Optional.empty();
    }

    BinarySearchTreeNode<E> insert(final Comparator<E> comparator, final E value) {
        for (BinarySearchTreeNode<E> cursor = this; ; ) {
            if (isValueLessThan(comparator, value)) {
                if (cursor.getRight() == null) {
                    cursor.setRight(new BinarySearchTreeNode<>(value, cursor));
                    return cursor.getRight();
                }
                cursor = cursor.getRight();
            } else {
                if (cursor.getLeft() == null) {
                    cursor.setLeft(new BinarySearchTreeNode<>(value, cursor));
                    return cursor.getLeft();
                }
                cursor = cursor.getLeft();
            }
        }
    }
}
