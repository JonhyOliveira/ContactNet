package dataStructures;

public interface OrderedSequence<E extends Comparable<E>> {

    /**
     * Inserts a new element in the ordered sequence
     * @param element the element to insert
     */
    void insert(E element);

    /**
     * Removes an element from the ordered sequence
     * @param element the element to remove
     * @return true if the element was found, false otherwise
     */
    boolean remove(E element);

    /**
     * Checks if the element is in the sequence
     * @param element element to check for
     * @return true if the element is in the sequence, false otherwise
     */
    boolean contains(E element);

    /**
     * Returns the element if it could be found
     *
     * @param element the element to find
     * @return the element found or null if it was not found
     */
    E get(E element);

    /**
     * @return the number of elements in the sequence
     */
    int size();

    /**
     * @return an ordered iterator
     */
    Iterator<E> iterator();
}
