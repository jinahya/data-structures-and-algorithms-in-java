package com.github.jinahya.data.structure.linear;

public abstract class LinkedListTest<L extends LinkedList<E>, E> extends ListTest<L, E> {

    // -----------------------------------------------------------------------------------------------------------------
    public LinkedListTest(final Class<L> listClass) {
        super(listClass);
    }
}
