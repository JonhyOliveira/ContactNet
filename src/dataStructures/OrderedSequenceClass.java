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
    	return get(element) != null;
    }

    @Override
    public E get(E element) {
        int index = sequence.find(element);
        if (index < 0) {
            return null;
        } else {
            return sequence.get(index);
        }
    }

    @Override
    public int size() {
        return sequence.size();
    }

    @Override
    public Iterator<E> iterator() {
        return sequence.iterator();
    }
}
