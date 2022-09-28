package priorityQ;

public abstract class MinPriorityQ<T> {
	private static final int DEFAULT_CAPACITY = 100;
	private static final int HEAP_ROOT = 1;

	private int _size;
	private int _capacity;
	private T[] _heap;

	public abstract boolean add(T anElement);

	public abstract T min();

	public abstract T removeMin();

	public abstract int size();
}
