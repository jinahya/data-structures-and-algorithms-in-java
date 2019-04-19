package com.github.jinahya.data.structure.linear;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SinglyLinkedList<E> implements LinkedList<E> {

    // -----------------------------------------------------------------------------------------------------------------
    SinglyLinkedListNode<E> nodeAt(final int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index(" + index + ") < 0");
        }
        SinglyLinkedListNode<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.getNext();
        }
        if (node == null) {
            throw new IndexOutOfBoundsException("no node at " + index);
        }
        return node;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public E getAt(final int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index(" + index + ") < 0");
        }
        return nodeAt(index).getValue();
    }

    @Override
    public E setAt(final int index, final E element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index(" + index + ") < 0");
        }
        if (index == 0) {
            if (head == null) {
                final SinglyLinkedListNode<E> node = new SinglyLinkedListNode<>();
                node.setValue(element);
                head = node;
                return null;
            } else {
                final E previous = head.getValue();
                head.setValue(element);
                return previous;
            }
        }
        final SinglyLinkedListNode<E> node = nodeAt(index);
        final E previous = node.getValue();
        node.setValue(element);
        return previous;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void insertAt(final int index, final E element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index(" + index + ") < 0");
        }
        if (index == 0) {
            if (head == null) {
                head = new SinglyLinkedListNode<>();
                head.setValue(element);
            } else {
                final SinglyLinkedListNode<E> node = new SinglyLinkedListNode<>();
                node.setValue(element);
                node.setNext(head);
                head = node;
            }
            return;
        }
        final SinglyLinkedListNode<E> node = nodeAt(index).insertNext(element);
    }

    @Override
    public E deleteAt(final int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index(" + index + ") < 0");
        }
        if (index == 0) {
            if (head == null) {
                throw new IndexOutOfBoundsException("no node at " + index);
            } else {
                final SinglyLinkedListNode<E> node = head;
                head = node.getNext();
                node.setNext(null);
                return node.getValue();
            }
        }
        return nodeAt(index - 1).deleteNext().getValue();
    }

    // -----------------------------------------------------------------------------------------------------------------
//    //@Override
//    public SinglyLinkedListNode<E> append(final E value) {
//        if (head == null) {
//            return insertAt(0, value);
//        }
//        final Optional<SinglyLinkedListNode<E>> last = traverse(n -> n.getNext() != null);
//        assert last.isPresent();
//        final SinglyLinkedListNode<E> node = new SinglyLinkedListNode<>();
//        node.setValue(value);
//        last.get().setNext(node);
//        return node;
//    }

    // -----------------------------------------------------------------------------------------------------------------
    private Optional<SinglyLinkedListNode<E>> traverse(final Predicate<SinglyLinkedListNode<E>> predicate) {
        SinglyLinkedListNode<E> curr = head;
        for (; curr != null; curr = curr.getNext()) {
            if (!predicate.test(curr)) {
                break;
            }
        }
        return Optional.ofNullable(curr);
    }

    private void iterate(final Consumer<SinglyLinkedListNode<E>> consumer) {
        for (SinglyLinkedListNode<E> curr = head; curr != null; curr = curr.getNext()) {
            consumer.accept(curr);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private SinglyLinkedListNode<E> head;
}
