package model;

public abstract class ReferenceListOrderedByCoordinate extends ReferenceList {

	private PointSet _pointSet;

	protected PointSet pointSet() {
		return this._pointSet;
	}

	private void setPointSet(PointSet newPointSet) {
		this._pointSet = newPointSet;
	}

	public ReferenceListOrderedByCoordinate(PointSet givenPointSet) {
		super(givenPointSet.size());
		this.setPointSet(givenPointSet);
		this.generateReferenceListOrderedByCoordinate();
	
	}

	protected abstract int coordinateReferencedByIndex(int i);

	private void swap(int i, int j) {
		Integer temp = this.elementAt(i);
		this.setElementAt(this.elementAt(j), i);
		this.setElementAt(temp, j);
	}

	private int compareCoordinate(int i, int j) {
		int coordinateReferenceBy_i = this.coordinateReferencedByIndex(i);
		int coordinateReferenceBy_j = this.coordinateReferencedByIndex(j);

		if (coordinateReferenceBy_i < coordinateReferenceBy_j) {
			return -1;
		} else if (coordinateReferenceBy_i > coordinateReferenceBy_j) {
			return +1;
		} else {
			if (this.elementAt(i) < this.elementAt(j)) {
				return -1;
			} else if (this.elementAt(i) > this.elementAt(j)) {
				return +1;
			} else {
				return 0;
			}
		}
	}

	private int pivotByMedian(int left, int right) {
		int mid = (left + right) / 2;
		if (this.compareCoordinate(left, mid) < 0) {
			if (this.compareCoordinate(mid, right) < 0) {
				return mid;
			} else if (this.compareCoordinate(left, right) < 0) {
				return right;
			} else {
				return left;
			}

		} else {
			if (this.compareCoordinate(right, mid) < 0) {
				return mid;
			} else if (this.compareCoordinate(right, left) < 0) {
				return right;
			} else {
				return left;
			}
		}
	}

	private int partition(int left, int right) {
		int pivotIndexByMedian = this.pivotByMedian(left, right);
		this.swap(left, pivotIndexByMedian);
		int pivotIndex = left;

		int toRight = left;
		int toLeft = right + 1;
		do {
			do {
				toRight++;
			} while (this.compareCoordinate(toRight, pivotIndex) < 0);
			do {
				toLeft--;
			} while (this.compareCoordinate(toLeft, pivotIndex) > 0);
			if (toRight < toLeft) {
				this.swap(toRight, toLeft);
			}
		} while (toRight < toLeft);
		this.swap(toLeft, pivotIndex);
		return toLeft;

	}

	private void qsortByCoordinateRecursively(int left, int right) {
		if (left < right) {
			int mid = this.partition(left, right);
			this.qsortByCoordinateRecursively(left, mid - 1);
			this.qsortByCoordinateRecursively(mid + 1, right);
		}
	}

	private void sortByCoordinate() {
		if (this.size() >= 2) {
			int indexOfMaxCoordinate = 0;
			for (int index = 1; index < this.size(); index++) {
				if (this.compareCoordinate(indexOfMaxCoordinate, index) < 0) {
					indexOfMaxCoordinate = index;
				}
			}
			this.swap(indexOfMaxCoordinate, this.size() - 1);
			this.qsortByCoordinateRecursively(0, this.size() - 2);
		}
	}

	private void generateReferenceListOrderedByCoordinate() {
		for (int index = 0; index < this.capacity(); index++) {
			this.add(Integer.valueOf(index));

		}
		this.sortByCoordinate();
	}
}
