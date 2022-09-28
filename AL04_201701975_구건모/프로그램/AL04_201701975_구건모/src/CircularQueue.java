
public class CircularQueue<T> implements Queue<T> {
	private T[] _elements;
	private int _capacity;
	private int _front;
	private int _rear;

	private T[] elements() {
		return this._elements;

	}

	private void setElements(T[] newElements) {
		this._elements = newElements;
	}

	private int capacity() {
		return this._capacity;

	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	private int front() {
		return this._front;
	}

	private void setFront(int newFront) {
		this._front = newFront;
	}

	private int rear() {
		return this._rear;
	}

	private void setRear(int newRear) {
		this._rear = newRear;

	}

	private int nextRear() {
		return (this.rear() + 1) % this.capacity();
	}

	private int nextFront() {
		return (this.front() + 1) % this.capacity();
	}

	@SuppressWarnings("unchecked")
	public CircularQueue(int maxNumberOfElement) {
		this.setCapacity(maxNumberOfElement);
		this.setElements((T[]) new Object[this.capacity()]);
		this.reset();
	}

	@Override
	public void reset() {
		this.setFront(0);
		this.setRear(0);
	}

	@Override
	public int size() {
		if (this.front() <= this.rear()) {
			return this.rear() - this.front();
		} else {
			return (this.capacity() + this.rear() + this.front());
		}
	}

	@Override
	public boolean isEmpty() {
		return (this.front() == this.rear());
	}

	@Override
	public boolean isFull() {
		return (this.front() == this.nextRear());
	}

	public boolean add(T anElement) {
		if (this.isFull()) {
			return false;
		} else {
			this.setRear(this.nextRear());
			this.elements()[this.rear()] = anElement;
			return true;
		}
	}

	public T remove() {
		if (this.isEmpty()) {
			return null;
		} else {
			this.setFront(this.nextFront());
			return this.elements()[this.front()];
		}
	}
}
