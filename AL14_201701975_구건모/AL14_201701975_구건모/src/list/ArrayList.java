package list;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 100;

	private int _capacity;
	private int _size;
	private T[] _elements;

	public ArrayList() {
		this(ArrayList.DEFAULT_CAPACITY);
	}

	public ArrayList(int givenCapacity) {
		this._capacity = givenCapacity;
		this._size = 0;
		this._elements = (T[]) new Object[this._capacity];

	}

	protected void setElementAt(T element, int order) {
		this._elements[order] = element;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return this._capacity;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (this.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		if (this.size() == this.capacity()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean orderIsValid(int order) {
		// TODO Auto-generated method stub
		if (order >= 0 && order < size()) {
			return true;
		}
		return false;
	}

	@Override
	public T elementAt(int order) {
		// TODO Auto-generated method stub
		if (this.orderIsValid(order)) {
			return this._elements[order];
		}
		return null;
	}

	@Override
	public boolean add(T elementForAdd) {
		if (!isFull()) {
			this._elements[_size++] = elementForAdd;
			return true;
		}
		return false;
	}

}
