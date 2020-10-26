package dataStructures;

public class OrderedSequenceClass<E extends Comparable<E>> implements OrderedSequence<E> {

    private List<E> sequence;

    public OrderedSequenceClass() {
        sequence = new DoublyLinkedList<>();
    }

    @Override
    public void insert(E element) {
        if (sequence.isEmpty())
            sequence.addFirst(element);
        else {
            Iterator<E> it = sequence.iterator();
            int pos = 0;
            while(it.hasNext()) {
                if (element.compareTo(it.next()) <= 0)
                    break;
                pos++;
            }

            sequence.add(pos, element);
        }

    }

    @Override
    public boolean remove(E element) {
        return sequence.remove(element);
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
        return sequence.iterator();
    }
}
