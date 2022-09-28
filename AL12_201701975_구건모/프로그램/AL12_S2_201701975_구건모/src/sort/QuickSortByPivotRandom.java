package sort;

import java.util.Random;

public class QuickSortByPivotRandom<E extends Comparable<E>> extends QuickSort<E> {

	private Random _random;

	public QuickSortByPivotRandom(boolean givenSortingOrder) {
		// TODO Auto-generated constructor stub
		super(givenSortingOrder);
		this.setRandom(new Random());
	}

	private Random random() {
		return this._random;
	}

	private void setRandom(Random newRandom) {
		this._random = newRandom;
	}

	@Override
	protected int pivot(E[] aList, int left, int right) {
		// TODO Auto-generated method stub
		int randomLocationFromLeft = this.random().nextInt(right - left + 1);
		int pivot = left + randomLocationFromLeft;
		return pivot;
	}

}
