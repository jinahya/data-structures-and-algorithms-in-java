package com.github.jinahya.data.structure.tree;

import java.util.Comparator;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class BinarySearchTree<E> implements RootedTree<E, BinarySearchTreeNode<E>> {

    // -----------------------------------------------------------------------------------------------------------------
    public static <T extends Comparable<? super T>> BinarySearchTree<T> newInstance() {
        return new BinarySearchTree<>(Comparator.<T>naturalOrder());
    }

    // -----------------------------------------------------------------------------------------------------------------
    public BinarySearchTree(final Comparator<E> comparator) {
        super();
        this.comparator = requireNonNull(comparator);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Optional<BinarySearchTreeNode<E>> getRoot() {
        return Optional.ofNullable(root);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Optional<BinarySearchTreeNode<E>> search(final E element) {
        return Optional.ofNullable(root).flatMap(r -> r.search(comparator, element));
    }

    public BinarySearchTreeNode<E> insert(final E element) {
        if (root == null) {
            root = new BinarySearchTreeNode<>(element, null);
            return root;
        }
        return root.insert(comparator, element);
    }

//    private BinarySearchTreeNode<E> deleteAt(@NonNull final BinarySearchTreeNode<E> node) {
//        final BinarySearchTreeNode<E> parent = node.getParent();
//        if (node.isLeaf()) { // left == null && right == null
//            if (parent != null) {
//                if (comparator.compare(parent.getValue(), node.getValue()) < 0) {
//                    parent.setRight(null);
//                } else {
//                    parent.setLeft(null);
//                }
//                return node.parent(null);
//            }
//        }
//        if (node.getLeft() == null) {
//            if (parent != null) {
//                if (comparator.compare(parent.getValue(), node.getValue()) < 0) {
//                    parent.setRight(null);
//                } else {
//                    parent.setLeft(null);
//                }
//                return node.parent(null);
//            }
//            if (node.getParent().getLeft() == node) {
//                node.getParent()
//            }
//            value = right.value;
//            left = right.left;
//            right = right.right;
//        }
//        if (right == null) {
//            value = left.value;
//            left = left.left;
//            right = left.right;
//        }
//        return;
//    }

    public Optional<BinarySearchTreeNode<E>> delete(final E element) {
        final Optional<BinarySearchTreeNode<E>> optional = search(element);
        if (!optional.isPresent()) {
            return optional;
        }
        final BinarySearchTreeNode<E> node = optional.get();
        final BinarySearchTreeNode<E> parent = node.getParent();
        if (node == root) {
            assert parent == null;
        } else {
            assert parent != null;
            if (node.isLeaf()) { // left == null && right == null;
                if (parent != null) {
                    if (node.isValueLessThan(comparator, parent.getValue())) { // left node
                        parent.setLeft(null);
                    } else {
                        parent.setRight((null));
                    }
                    node.setParent(null);
                }
            } else if (node.getLeft() == null) { // node.right != null
                final BinarySearchTreeNode<E> right = node.getRight();
                assert right != null;
                if (parent != null) {
                    if (node.isValueLessThan(comparator, parent.getValue())) {
                        parent.setLeft(right);
                    } else {
                        parent.setRight(right);
                    }
                    node.setParent(null);
                }
            } else if (node.getRight() == null) { // node.left != null
                final BinarySearchTreeNode<E> left = node.getLeft();
                assert left != null;
                if (parent != null) {
                    if (node.isValueLessThan(comparator, parent.getValue())) {
                        parent.setLeft(left);
                    } else {
                        parent.setRight(left);
                    }
                    node.setParent(null);
                }
            } else { // left != null && right != null
                final BinarySearchTreeNode<E> leftMost = node.getRight().getLeftMost();
                assert leftMost != null;
//                if (leftMostOptional.isPresent()) {
//                } else {
//                    final BinarySearchTreeNode<E> left = node.getLeft();
//                }
            }
            node.setParent(null);
        }
        return Optional.of(node);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Comparator<E> getComparator() {
        return comparator;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final Comparator<E> comparator;

    private BinarySearchTreeNode<E> root;
}
