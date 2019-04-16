package com.github.jinahya.data.structure.tree;

import java.util.Optional;

public interface RootedTree<E, N extends RootedTreeNode<E>> extends Tree<E> {

    // -----------------------------------------------------------------------------------------------------------------
    Optional<N> getRoot();
}
