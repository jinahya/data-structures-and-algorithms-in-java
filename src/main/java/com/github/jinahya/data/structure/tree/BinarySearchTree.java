package com.github.jinahya.data.structure.tree;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Slf4j
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
    public Optional<BinarySearchTreeNode<E>> search(@NonNull final E element) {
        return Optional.ofNullable(root).flatMap(r -> r.search(comparator, element));
    }

    public BinarySearchTreeNode<E> insert(@NonNull final E element) {
        if (root == null) {
            root = new BinarySearchTreeNode<>(null, element);
            return root;
        }
        return root.insert(comparator, element);
    }

//    private BinarySearchTreeNode<E> delete(@NonNull final BinarySearchTreeNode<E> node) {
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

    public Optional<BinarySearchTreeNode<E>> leftMost(final BinarySearchTreeNode<E> node) {
        BinarySearchTreeNode<E> cursor = node.getLeft();
        return null;
    }

    public boolean delete(@NonNull final E element) {
        final Optional<BinarySearchTreeNode<E>> optional = search(element);
        if (!optional.isPresent()) {
            return false;
        }
        final BinarySearchTreeNode<E> node = optional.get();
        if (node == root) {
        }
        final BinarySearchTreeNode<E> parent = node.getParent();
        if (node.isLeaf()) { // left == null && right == null;
            if (node.isValueLessThan(comparator, parent.getValue())) {
                parent.setLeft(null);
            } else {
                parent.setRight((null));
            }
        }
        if (node.getLeft() == null) {
            parent.setRight(node.getRight());
        }
        if (node.getRight() == null) {
            parent.setLeft(node.getLeft());
        }
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Comparator<E> getComparator() {
        return comparator;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final Comparator<E> comparator;

    private BinarySearchTreeNode<E> root;
}
