package com.github.jinahya.data.structure.linear;

public interface List<E> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    E getAt(int index);

    E setAt(int index, E value);

    // -----------------------------------------------------------------------------------------------------------------
    void insertAt(int index, E value);

    E deleteAt(int index);

    // -----------------------------------------------------------------------------------------------------------------
    //N append(E value);
}
