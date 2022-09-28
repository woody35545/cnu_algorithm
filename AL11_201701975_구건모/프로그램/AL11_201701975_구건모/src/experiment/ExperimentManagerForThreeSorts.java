package experiment;

import app.AppView;
import sort.HeapSort;
import sort.InsertionSort;
import sort.QuickSortByPivotLeft;

public class ExperimentManagerForThreeSorts extends ExperimentManager {
	private static final boolean DEBUG_MODE = false;

	private static void showDebugMessage(String aMessage) {
		if (ExperimentManagerForThreeSorts.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}

	private static final InsertionSort<Integer> InsertionSort = new InsertionSort<Integer>(true);
	private static final QuickSortByPivotLeft<Integer> QuickSort = new QuickSortByPivotLeft<Integer>(true);
	private static final HeapSort<Integer> HeapSort = new HeapSort<Integer>(true);

	private long[] _measurementForInsertionSort;
	private long[] _measurementForQuickSort;
	private long[] _measurementForHeapSort;
	private long[] _estimationForInsertionSort;
	private long[] _estimationForQuickSort;
	private long[] _estimationForHeapSort;

	private long[] measurementForInsertionSort() {
		return this._measurementForInsertionSort;
	}

	private void setMeasurementForInsertionSort(long[] newMeasurement) {
		this._measurementForInsertionSort = newMeasurement;
	}

	private long[] measurementForQuickSort() {
		return this._measurementForQuickSort;
	}

	private void setMeasurementForQuickSort(long[] newMeasurement) {
		this._measurementForQuickSort = newMeasurement;
	}

	private long[] measurementForHeapSort() {
		return this._measurementForHeapSort;
	}

	private void setMeasurementForHeapSort(long[] newMeasurement) {
		this._measurementForHeapSort = newMeasurement;
	}

	private long[] estimationtForInsertionSort() {
		return this._estimationForInsertionSort;
	}

	private void setEstimationForInsertionSort(long[] newMeasurement) {
		this._estimationForInsertionSort = newMeasurement;
	}

	private long[] estimationForQuickSort() {
		return this._estimationForQuickSort;
	}

	private void setEstimationForQuickSort(long[] newMeasurement) {
		this._estimationForQuickSort = newMeasurement;
	}

	private long[] estimationForHeapSort() {
		return this._estimationForHeapSort;
	}

	private void setEstimationForHeapSort(long[] newMeasurement) {
		this._estimationForHeapSort = newMeasurement;
	}

	@Override
	protected void performMeasuring(ListOrder anOrder) {
		// TODO Auto-generated method stub

		Integer[] experimentList = this.dataSet().listWithOrder(anOrder);
		this.setMeasurementForInsertionSort(
				this.experiment().durationOfSort(ExperimentManagerForThreeSorts.InsertionSort, experimentList));
		ExperimentManagerForThreeSorts.showDebugMessage("[Debug] end of Insertion Sort\n");

		this.setMeasurementForQuickSort(
				this.experiment().durationOfSort(ExperimentManagerForThreeSorts.QuickSort, experimentList));
		ExperimentManagerForThreeSorts.showDebugMessage("[Debug] end of Quick Sort\n");

		this.setMeasurementForHeapSort(
				this.experiment().durationOfSort(ExperimentManagerForThreeSorts.HeapSort, experimentList));
		ExperimentManagerForThreeSorts.showDebugMessage("[Debug] end of HeapSort Sort\n");

	}

	private void estimateForRandomList() {
		this.setEstimationForInsertionSort(
				Estimation.estimateByQudratic(this.measurementForInsertionSort(), this.parameterSetForMeasurement()));
		this.setEstimationForQuickSort(
				Estimation.estimateByNLogN(this.measurementForQuickSort(), this.parameterSetForMeasurement()));
		this.setEstimationForHeapSort(
				Estimation.estimateByNLogN(this.measurementForHeapSort(), this.parameterSetForMeasurement()));
	}

	private void estimateForAscendingList() {
		this.setEstimationForInsertionSort(
				Estimation.estimateByLinear(this.measurementForInsertionSort(), this.parameterSetForMeasurement()));
		this.setEstimationForQuickSort(
				Estimation.estimateByQudratic(this.measurementForQuickSort(), this.parameterSetForMeasurement()));
		this.setEstimationForHeapSort(
				Estimation.estimateByNLogN(this.measurementForHeapSort(), this.parameterSetForMeasurement()));
	}

	private void estimateForDescendingList() {
		this.setEstimationForInsertionSort(
				Estimation.estimateByQudratic(this.measurementForInsertionSort(), this.parameterSetForMeasurement()));
		this.setEstimationForQuickSort(
				Estimation.estimateByQudratic(this.measurementForQuickSort(), this.parameterSetForMeasurement()));
		this.setEstimationForHeapSort(
				Estimation.estimateByNLogN(this.measurementForHeapSort(), this.parameterSetForMeasurement()));
	}

	@Override
	public void performExperiment(ListOrder anOrder) {
		// TODO Auto-generated method stub
		this.performMeasuring(anOrder);
		if (anOrder.equals(ListOrder.Random)) {
			this.estimateForRandomList();
		} else if (anOrder.equals(ListOrder.Ascending)) {
			this.estimateForAscendingList();
		} else {
			this.estimateForDescendingList();
		}

	}

	public long measurementForInsertionsorAt(int anIndex) {
		return this.measurementForInsertionSort()[anIndex];
	}

	public long measurementForQuickSortAt(int anIndex) {
		return this.measurementForQuickSort()[anIndex];

	}

	public long measurementForHeapSortAt(int anIndex) {
		return this.measurementForHeapSort()[anIndex];
	}

	public long estimationForInsertionSortAt(int anIndex) {
		return this.estimationtForInsertionSort()[anIndex];
	}

	public long estimationForQuickSortAt(int anIndex) {
		return this.estimationForQuickSort()[anIndex];
	}

	public long estimationForHeapSortAt(int anIndex) {
		return this.estimationForHeapSort()[anIndex];
	}
}
