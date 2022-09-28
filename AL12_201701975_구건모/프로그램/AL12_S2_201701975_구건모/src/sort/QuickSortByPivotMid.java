package sort;

public class QuickSortByPivotMid<E extends Comparable<E>> extends QuickSort<E> {

	public QuickSortByPivotMid(boolean givenSortingOrder) {
		super(givenSortingOrder);
		// TODO Auto-generated constructor stub
	}

	protected int pivot(E[] aList, int left, int right) {
		return ((left + right) / 2);
	}

}
