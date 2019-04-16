package com.github.jinahya.data.structure.tree;

public interface SingleParentedTreeNode<E, T extends SingleParentedTreeNode<E, T>> extends TreeNode<E> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the parent node of this node.
     *
     * @return the parent node of this node; {@code null} if this node is a root node.
     */
    T getParent();
}
