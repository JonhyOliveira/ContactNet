package dataStructures;

import exceptions.NoElementException;

public class StackClass<E> extends SinglyLinkedList<E> implements Stack<E> {

    // Stack initializer
    public StackClass() {
        super();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public E top() throws NoElementException {
        return super.getFirst();
    }

    @Override
    public void push(E element) {
        super.addFirst(element);
    }

    @Override
    public E pop() throws NoElementException {
        return super.removeFirst();
    }
}
