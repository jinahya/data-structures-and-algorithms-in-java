package com.github.jinahya.data.structure.linear;

class SinglyLinkedListNode<E> extends AbstractLinkedListNode<E> {

    // -----------------------------------------------------------------------------------------------------------------
    SinglyLinkedListNode<E> insertNext(final E value) {
        final SinglyLinkedListNode<E> node = new SinglyLinkedListNode<>();
        node.setValue(value);
        node.setNext(getNext());
        setNext(node);
        return node;
    }

    SinglyLinkedListNode<E> deleteNext() {
        final SinglyLinkedListNode<E> node = getNext();
        if (node != null) {
            setNext(node.getNext());
            node.setNext(null);
        }
        return node;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public SinglyLinkedListNode<E> getNext() {
        return next;
    }

    public void setNext(final SinglyLinkedListNode<E> next) {
        this.next = next;
    }

    public SinglyLinkedListNode<E> next(final SinglyLinkedListNode<E> next) {
        setNext(next);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private SinglyLinkedListNode<E> next;
}
