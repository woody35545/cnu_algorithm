package sort;

public class QuickSortByPivotMid<E extends Comparable<E>> extends QuickSort<E> {

	public QuickSortByPivotMid(boolean givenSortingOrder) {
		super(givenSortingOrder);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int pivot(E[] aList, int left, int right) {
		// TODO Auto-generated method stub
		return ((left + right) / 2);
	}

}
