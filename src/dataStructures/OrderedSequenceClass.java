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
    	int index = sequence.find(element);
    	if (index != -1) {
    		return true;
    	}else {
    		return false;
    	}
    }

    @Override
    public E get(E element) {
    	int index = sequence.find(element);
    	if(index == -1) {
    		return null;
    	}else {
    		E elem = sequence.get(index);
    		return elem;
    	}
    }

    @Override
    public Iterator<E> iterator() {
    	Iterator<E> it;
    	it = sequence.iterator();
        return it;
    }
}
