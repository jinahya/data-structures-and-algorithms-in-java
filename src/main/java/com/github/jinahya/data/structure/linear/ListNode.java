package com.github.jinahya.data.structure.linear;

import java.io.Serializable;

interface ListNode<E> extends Serializable {

    // -----------------------------------------------------------------------------------------------------------------
    E getValue();

    void setValue(E value);
}
