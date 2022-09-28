package sort;

public class InsertionSort<E extends Comparable<E>> extends Sort<E> {

	public InsertionSort(boolean givenSortingOrder) {
		super(givenSortingOrder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean sort(E[] aList) {
		// TODO Auto-generated method stub
		if (aList.length <= 1) {
			return false;

		}
		int minLoc = 0;
		for (int i = 0; i < aList.length; i++) {
			if (this.compare(aList[minLoc], aList[i]) > 0) {
				minLoc = i;
			}

		}
		this.swap(aList, minLoc, 0);

		for (int i = 2; i < aList.length; i++) {
			E insertedElement = aList[i];
			int j = i - 1;
			while (this.compare(aList[j], insertedElement) > 0) {
				aList[j + 1] = aList[j];
				j--;
			}
			aList[j + 1] = insertedElement;
		}
		return true;

	}

}
