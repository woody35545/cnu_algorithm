package experiment;

public class ParameterSetForMeasurement extends ParameterSetForIteration {
	private static final int DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT = 1;

	private int _numberOfRepititionOfSingleSort;

	public int numberOfRepititionOfSingleSort() {
		return this._numberOfRepititionOfSingleSort;

	}

	public void setNumberOfRepititionOfSingleSort(int newNumberOfRepititionOfSingleSort) {
		this._numberOfRepititionOfSingleSort = newNumberOfRepititionOfSingleSort;
	}

	public ParameterSetForMeasurement() {
		super();
		this.setNumberOfRepititionOfSingleSort(DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT);
	}

	public ParameterSetForMeasurement(int givenStartingSize, int givenNumberOfIteration, int givenIncrementSize,
			int givenNumberOfRepititionOfSingleSort) {
		super(givenStartingSize, givenNumberOfIteration, givenIncrementSize);
		this.setNumberOfRepititionOfSingleSort(givenNumberOfRepititionOfSingleSort);
	}
}
