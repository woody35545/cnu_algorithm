package list;

public class ArrayList<T> extends List<T> {
	private static final int DEFAULT_CAPACITY = 10;

	private int _capacity;
	private int _size;
	private T[] _elements;

	public ArrayList() {
		this(ArrayList.DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((T[]) new Object[this.capactiy()]);
		this.setSize(0);
	}

	private int capactiy() {
		return this._capacity;

	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._size;
	}

	private void setSize(int newSize) {
		this._size = newSize;
	}

	private T[] elements() {
		return this._elements;

	}

	private void setElements(T[] newElements) {
		this._elements = newElements;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.size() == 0);
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return (this.size() == this.capactiy());
	}

	@Override
	public boolean add(T anElement) {
		// TODO Auto-generated method stub
		return this.addToLast(anElement);

	}

	private boolean addToLast(T anElement) {
		if (!this.isFull()) {
			this.elements()[this.size()] = anElement;
			this.setSize(this.size() + 1);
			return true;

		}
		return false;
	}

	@Override
	public T removeAny() {
		// TODO Auto-generated method stub
		return this.removeLast();

	}

	private T removeLast() {
		if (!this.isEmpty()) {
			this.setSize(this.size() - 1);
			T removedElement = this.elements()[this.size()];
			this.elements()[this.size()] = null;
			return removedElement;

		}
		return null;

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.size(); i++) {
			this.elements()[i] = null;
		}
		this.setSize(0);
	}

	@Override
	public Iterator<T> listIterator() {
		// TODO Auto-generated method stub
		return new IteratorForArrayList();
	}

	private class IteratorForArrayList implements Iterator<T> {
		private int _nextPosition;

		private int nextPosition() {
			return this._nextPosition;
		}

		private void setNextPosition(int newPosition) {
			this._nextPosition = newPosition;

		}

		private IteratorForArrayList() {
			this.setNextPosition(0);
		}

		public boolean hasNext() {
			return (this.nextPosition() < ArrayList.this.size());

		}

		public T next() {
			T nextElement = ArrayList.this.elements()[this.nextPosition()];
			this.setNextPosition(this.nextPosition() + 1);
			return nextElement;
		}

	}

}
