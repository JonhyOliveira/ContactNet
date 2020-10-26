package dataStructures;

public class OrderedSequenceClass<E extends Comparable<E>> implements OrderedSequence<E> {

    private List<E> sequence;

    public OrderedSequenceClass() {
        sequence = new DoublyLinkedList<>();
    }

    @Override
    public void insert(E element) {
        Iterator<E> it = sequence.iterator();
        if (sequence)
    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public E get(E element) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
