package com.github.jinahya.data.structure.tree;

import lombok.NonNull;

import java.util.Comparator;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

// -----------------------------------------------------------------------------------------------------------------
public class BinarySearchTreeNode<E>
        implements RootedTreeNode<E>, SingleParentedTreeNode<E, BinarySearchTreeNode<E>> {

    // -------------------------------------------------------------------------------------------------------------
    BinarySearchTreeNode(final BinarySearchTreeNode<E> parent, @NonNull final E value) {
        super();
        this.parent = parent;
        this.value = requireNonNull(value);
    }

    // -------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "parent.value=" + ofNullable(parent).map(p -> p.value).orElse(null)
               + ",value=" + value
               + ",left=" + left
               + ",right=" + right
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------
    boolean isValueLessThan(@NonNull final Comparator<E> comparator, @NonNull final E value) {
        return comparator.compare(this.value, value) < 0;
    }

    Optional<BinarySearchTreeNode<E>> search(@NonNull final Comparator<E> comparator, @NonNull final E element) {
        for (BinarySearchTreeNode<E> cursor = this; cursor != null; ) {
            if (cursor.getValue().equals(element)) {
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

    BinarySearchTreeNode<E> insert(@NonNull final Comparator<E> comparator, @NonNull final E value) {
        for (BinarySearchTreeNode<E> cursor = this; ; ) {
            if (isValueLessThan(comparator, value)) {
                if (cursor.getRight() == null) {
                    cursor.setRight(new BinarySearchTreeNode<>(cursor, value));
                    return cursor.getRight();
                }
                cursor = cursor.getRight();
            } else {
                if (cursor.getLeft() == null) {
                    cursor.setLeft(new BinarySearchTreeNode<>(cursor, value));
                    return cursor.getLeft();
                }
                cursor = cursor.getLeft();
            }
        }
    }

    // -------------------------------------------------------------------------------------------------------------
    @Override
    public E getValue() {
        return value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public boolean isRoot() {
        return getParent() == null;
    }

    // -------------------------------------------------------------------------------------------------------------
    @Override
    public boolean isLeaf() {
        return left == null && right == null;
    }

    // -------------------------------------------------------------------------------------------------------------
    @Override
    public BinarySearchTreeNode<E> getParent() {
        return parent;
    }

    void setParent(final BinarySearchTreeNode<E> parent) {
        this.parent = parent;
    }

    BinarySearchTreeNode<E> parent(final BinarySearchTreeNode<E> parent) {
        setParent(parent);
        return this;
    }

    // -------------------------------------------------------------------------------------------------------------
    public BinarySearchTreeNode<E> getLeft() {
        return left;
    }

    void setLeft(final BinarySearchTreeNode<E> left) {
        if (this.left != null) {
            this.left.setParent(null);
        }
        this.left = left;
        if (this.left != null) {
            this.left.setParent(this);
        }
    }

    BinarySearchTreeNode<E> left(final BinarySearchTreeNode<E> left) {
        setLeft(left);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public BinarySearchTreeNode<E> getRight() {
        return right;
    }

    void setRight(final BinarySearchTreeNode<E> right) {
        if (this.right != null) {
            this.right.setParent(null);
        }
        this.right = right;
        if (this.right != null) {
            this.right.setParent(this);
        }
    }

    BinarySearchTreeNode<E> right(final BinarySearchTreeNode<E> right) {
        setRight(right);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    Optional<BinarySearchTreeNode<E>> getLeftMost() {
        if (left == null) {
            return Optional.empty();
        }
        BinarySearchTreeNode<E> cursor = left;
        while (cursor.left != null) {
            cursor = cursor.left;
        }
        return Optional.of(cursor);
    }

    Optional<BinarySearchTreeNode<E>> getRightMost() {
        if (right == null) {
            return Optional.empty();
        }
        BinarySearchTreeNode<E> cursor = right;
        while (cursor.right != null) {
            cursor = cursor.right;
        }
        return Optional.of(cursor);
    }

    // -------------------------------------------------------------------------------------------------------------
    private BinarySearchTreeNode<E> parent;

    private final E value;

    // -------------------------------------------------------------------------------------------------------------
    private BinarySearchTreeNode<E> left;

    private BinarySearchTreeNode<E> right;
}
