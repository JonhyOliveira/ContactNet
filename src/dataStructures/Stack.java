package dataStructures;

import exceptions.NoElementException;

/**
 * Stack Abstract Data Type using FILO insertion
 * Includes description of general methods to be implemented by stacks.
 * @author Joao Oliveira 58001 & Rafel Borralho 58349
 * @version final
 * @param <E> Generic type element
 */
public interface Stack<E> {

    /**
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * @return the number of elements on the stack
     */
    int size();

    /**
     * Peeks at element at top of the stack
     * @return the element at the top of the stack
     * @throws NoElementException if the stack is empty
     */
    E top() throws NoElementException;

    /**
     * Puts the element at the top of the stack
     * @param element element to insert
     */
    void push(E element);

    /**
     * Removes the element at the top of the stack
     * @return the element at the top of the stack
     * @throws NoElementException if the stack is empty
     */
    E pop() throws NoElementException;
}
