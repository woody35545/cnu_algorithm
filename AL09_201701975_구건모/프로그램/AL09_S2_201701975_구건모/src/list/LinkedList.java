package list;

public class LinkedList<T> extends List<T> {
	private LinkedNode<T> _head;
	private int _size;

	public LinkedList() {
		this.reset();
	}

	private LinkedNode<T> head() {
		return this._head;
	}

	private void setHead(LinkedNode<T> newHead) {
		this._head = newHead;
	}

	public int size() {
		return this._size;
	}

	private void setSize(int newSize) {
		this._size = newSize;
	}

	public boolean isEmpty() {
		return (this.size() == 0);
	}

	public boolean isFull() {
		return false;
	}

	public boolean add(T anElement) {
		LinkedNode<T> addedNode = new LinkedNode<T>(anElement, null);
		addedNode.setNext(this.head());
		this.setHead(addedNode);
		this.setSize(this.size() + 1);
		return true;
	}

	public T removeAny() {
		if (this.isEmpty()) {
			return null;

		} else {
			T removedElement = this.head().element();
			this.setHead(this.head().next());
			this.setSize(this.size() - 1);
			return removedElement;
		}
	}

	public void reset() {
		this.setSize(0);
		this.setHead(null);
	}

	public Iterator<T> listIterator() {
		return new IteratorOfrLinkedList();
	}

	public class IteratorOfrLinkedList implements Iterator<T> {
		LinkedNode<T> _nextNode;

		private LinkedNode<T> nextNode() {
			return this._nextNode;
		}

		private void setNextNode(LinkedNode<T> newLinkedNode) {
			this._nextNode = newLinkedNode;
		}

		private IteratorOfrLinkedList() {
			this.setNextNode((LinkedNode<T>) LinkedList.this.head());
		}

		public boolean hasNext() {
			return (this.nextNode() != null);
		}

		public T next() {
			T nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}

	}

}
