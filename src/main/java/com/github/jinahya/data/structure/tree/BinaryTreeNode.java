package com.github.jinahya.data.structure.tree;

import com.github.jinahya.data.structure.Node;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

// -----------------------------------------------------------------------------------------------------------------
public class BinaryTreeNode<E, T extends BinaryTreeNode<E, T>>
        implements RootedTreeNode<E>, SingleParentedTreeNode<E, T> {

    // -----------------------------------------------------------------------------------------------------------------
    public enum Side {
        LEFT,
        RIGHT
    }

    // -------------------------------------------------------------------------------------------------------------
    BinaryTreeNode(final E value, final T parent) {
        super();
        this.value = requireNonNull(value, "value is null");
        this.parent = parent;
    }

    // -------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "value=" + value
               + ",parent.value=" + ofNullable(parent).map(Node::getValue).orElse(null)
               + ",left=" + left
               + ",right=" + right
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------
    public int compareValueTo(final Comparator<E> comparator, final E value) {
        return comparator.compare(this.value, value);
    }

    public boolean isValueLessThan(final Comparator<E> comparator, final E value) {
        return compareValueTo(comparator, value) < 0;
    }

    // -----------------------------------------------------------------------------------------------------------------
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
    public T getParent() {
        return parent;
    }

    void setParent(final T parent) {
        if (this == parent) {
            throw new IllegalArgumentException("this(" + this + ") == parent(" + parent + ")");
        }
        if (this.parent != null) {
            if (side == Side.LEFT) {
                this.parent.setLeft(null);
            } else {
                this.parent.setRight(null);
            }
        }
        this.parent = parent;
    }

    @SuppressWarnings({"unchecked"})
    T parent(final T parent) {
        setParent(parent);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Side getSide() {
        return side;
    }

    void setSide(final Side side) {
        this.side = side;
    }

    @SuppressWarnings({"unchecked"})
    T side(final Side side) {
        setSide(side);
        return (T) this;
    }

    // -------------------------------------------------------------------------------------------------------------
    public T getLeft() {
        return left;
    }

    @SuppressWarnings({"unchecked"})
    void setLeft(final T left) {
        if (this == left) {
            throw new IllegalArgumentException("this(" + this + ") == left(" + left + ")");
        }
        if (this.left != null) {
            this.left.setSide(null);
            this.left.setParent(null);
        }
        this.left = left;
        if (this.left != null) {
            this.left.setSide(Side.LEFT);
            this.left.setParent((T) this);
        }
    }

    @SuppressWarnings({"unchecked"})
    T left(final T left) {
        setLeft(left);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public T getRight() {
        return right;
    }

    @SuppressWarnings({"unchecked"})
    void setRight(final T right) {
        if (this == right) {
            throw new IllegalArgumentException("this(" + this + ") == right(" + right + ")");
        }
        if (this.right != null) {
            this.right.setSide(null);
            this.right.setParent(null);
        }
        this.right = right;
        if (this.right != null) {
            this.right.setSide(Side.RIGHT);
            this.right.setParent((T) this);
        }
    }

    @SuppressWarnings({"unchecked"})
    T right(final T right) {
        setRight(right);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    T getLeftMost() {
        @SuppressWarnings({"unchecked"})
        T cursor = (T) this;
        for (T most = cursor.getLeft(); most != null; most = cursor.getLeft()) {
            cursor = most;
        }
        return cursor;
    }

    T getRightMost() {
        @SuppressWarnings({"unchecked"})
        T cursor = (T) this;
        for (T most = cursor.getRight(); most != null; most = cursor.getRight()) {
            cursor = most;
        }
        return cursor;
    }

    // -------------------------------------------------------------------------------------------------------------
    public void setValue(E value) {

    }

    private final E value;

    // -----------------------------------------------------------------------------------------------------------------
    private T parent;

    private Side side;

    // -----------------------------------------------------------------------------------------------------------------
    private T left;

    private T right;
}
