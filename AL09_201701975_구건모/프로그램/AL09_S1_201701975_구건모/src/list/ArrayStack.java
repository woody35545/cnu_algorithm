package list;

public class ArrayStack<E> implements Stack<E> {
	private static final int DEFAULT_CAPACITY = 100;

	private int _capacity;
	private int _top;
	private E[] _elements;

	public ArrayStack() {
		this(ArrayStack.DEFAULT_CAPACITY);

	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((E[]) new Object[this.capacity()]);
		this.setTop(-1);
	}

	private int capacity() {
		return this._capacity;
	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	private int top() {
		return this._top;
	}

	private void setTop(int newTop) {
		this._top = newTop;
	}

	protected E[] elements() {
		return this._elements;

	}

	private void setElements(E[] newElements) {
		this._elements = newElements;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.setTop(-1);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.top() + 1;
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
	public boolean push(E anElement) {
		// TODO Auto-generated method stub

		if (!this.isFull()) {
			this.elements()[this.top() + 1] = anElement;
			this.setTop(this.top() + 1);

			return true;
		}
		return false;
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		if (!this.isEmpty()) {
			E top_element = this.elements()[this.top()];
			this.setTop(this.top() - 1);
			return top_element;

		}

		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if (!this.isEmpty()) {
			return this.elements()[this.top()];
		}
		System.out.println("*** pop() ***");

		return null;
	}

}
