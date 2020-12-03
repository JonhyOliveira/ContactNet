package dataStructures;

import exceptions.InvalidPositionException;
import exceptions.NoElementException;

/**
 * Singly linked list Implementation 
 * @author AED Team
 * @version 1.0
 * @param <E> Generic Element
 */
public class SinglyLinkedList<E> implements List<E> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	static class SListNode<E>{
		// Element stored in the node.
		protected E element;
		// (Pointer to) the next node.
		protected SListNode<E> next;

		public SListNode( E elem, SListNode<E> theNext ){
			element = elem;
			next = theNext;
		}

		public SListNode( E theElement ){
			this(theElement, null);
		}

		public E getElement( ){
			return element;
		}

		public SListNode<E> getNext( ){
			return next;
		}

		public void setElement( E newElement ){
			element = newElement;
		}

		public void setNext( SListNode<E> newNext ){
			next = newNext;
		}

	}

	// Node at the head of the list.
	protected SListNode<E> head;

	// Node at the tail of the list.
	protected SListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;

	public SinglyLinkedList() {
		head = tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public Iterator<E> iterator() throws NoElementException {
		return new SinglyLLIterator<>(head);
	}

	@Override
	public int find(E element) {
		SListNode<E> auxNode = head;
		for(int i = 0; auxNode != null; i++, auxNode = auxNode.getNext())
			if (auxNode.getElement().equals(element))
				return i;
		return -1;
	}

	@Override
	public E getFirst() throws NoElementException {

		if (isEmpty()) throw new NoElementException();

		return head.getElement();
	}

	@Override
	public E getLast() throws NoElementException {

		if (isEmpty()) throw new NoElementException();

		return tail.getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {

		if (position < 0 || position >= size())
			throw new InvalidPositionException();
		SListNode<E> auxNode = head;
		for (int i = 0; i < position; i++)
			auxNode = auxNode.getNext();

		return auxNode.getElement();
	}

	@Override
	public void addFirst(E element) {

		head = new SListNode<>(element, head);
		if (size() == 0)
			tail = head;
		currentSize++;
	}

	@Override
	public void addLast(E element) {

		if (tail != null) {
			tail.setNext(new SListNode<>(element));
			tail = tail.getNext();
		}
		else
			tail = new SListNode<>(element);
			if (size() == 0)
				head = tail;
		currentSize++;
	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position < 0 || position > size()) // if position == size() insert at the end
			throw new InvalidPositionException();

		if (position == 0)
			addFirst(element);
		else if (position == size())
			addLast(element);
		else {
			SListNode<E> auxNode = head;
			for (int i = 0; i < position - 1; i++)
				auxNode = auxNode.getNext();

			auxNode.setNext(new SListNode<>(element, auxNode.getNext()));
		}


		currentSize++;
	}

	@Override
	public E removeFirst() throws NoElementException {

		if (isEmpty())
			throw new NoElementException();

		SListNode<E> auxNode = head;
		head = head.getNext();
		currentSize--;
		return auxNode.getElement();
	}

	@Override
	public E removeLast() throws NoElementException {

		if (isEmpty())
			throw new NoElementException();

		E last = tail.getElement();

		SListNode<E> auxNode = head;
		while(auxNode.getNext() != tail)
			auxNode = auxNode.getNext();

		auxNode.setNext(null);
		tail = auxNode;

		return last;
	}

	@Override
	public E remove(int position) throws InvalidPositionException {

		if (position < 0 || position >= size())
			throw new InvalidPositionException();

		E element;

		if (position == 0) { // remove head (handles case where currentSize = 1)
			element = head.getElement();
			head = head.getNext();
		}
		else { // remove other

			SListNode<E> prevNode = head;
			for (int i = 0; i<position; i++, prevNode = prevNode.getNext()); // go up to the node before the one we want to land on

			SListNode<E> nextNode = prevNode.getNext().getNext();

			element = prevNode.getNext().getElement();

			prevNode.setNext(nextNode);
		}

		currentSize--;
		return element;
	}

	@Override
	public boolean remove(E element) {
		int pos = find(element);

		if (pos >= 0) {
			remove(pos);
			return true;
		}
		else
			return false;
	}
}
