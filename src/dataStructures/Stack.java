package dataStructures;

public interface Stack<E> {

    boolean isEmpty();

    int size();

    E top() throws NoElementException;

    void push(E element);

    E pop() throws NoElementException;
}
