package com.github.jinahya.data.structure.linear;

abstract class AbstractListNode<E> implements ListNode<E> {

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public E getValue() {
        return value;
    }

    @Override
    public void setValue(final E value) {
        this.value = value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private E value;
}
