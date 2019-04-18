package com.github.jinahya.data.structure.linear;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SinglyLinkedListTest<L extends SinglyLinkedList<E>, E> extends LinkedListTest<L, E> {

    // -----------------------------------------------------------------------------------------------------------------
    public SinglyLinkedListTest(final Class<L> listClass) {
        super(listClass);
    }
}
