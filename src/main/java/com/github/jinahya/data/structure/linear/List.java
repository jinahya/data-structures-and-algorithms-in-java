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

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    E setAt(int index, E element);

    // -----------------------------------------------------------------------------------------------------------------
    void insertAt(int index, E element);

    E deleteAt(int index);

    // -----------------------------------------------------------------------------------------------------------------
    //N append(E value);
}
