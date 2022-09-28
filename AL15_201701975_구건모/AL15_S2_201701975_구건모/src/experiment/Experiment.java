package experiment;

import model.FindClosestPair;
import model.PointSet;

public abstract class Experiment {
	private ParameterSet _parameterSet;
	private FindClosestPair _findClosestPair;

	private ParameterSet ParameterSet() {
		return this._parameterSet;
	}

	private void setParameterSet(ParameterSet newParamenterSet) {
		this._parameterSet = newParamenterSet;
	}

	protected FindClosestPair findClosestPair() {
		return this._findClosestPair;
	}

	private void setFindClosetPair(FindClosestPair newFindClosestPair) {
		this._findClosestPair = newFindClosestPair;
	}

	protected Experiment(FindClosestPair givenFindClosestPair, ParameterSet givenParameterSet) {
		this.setFindClosetPair(givenFindClosestPair);
		this.setParameterSet(givenParameterSet);
	}

	public abstract long durationOfSingleSolve(PointSet pointSet);

	public long averageDurationOfSingleSolves(PointSet pointSet) {
		long sum = 0;
		for (int count = 0; count < this.ParameterSet().numberOfRepetitionOfSameExecution(); count++) {
			sum += this.durationOfSingleSolve(pointSet);
		}
		long average = sum / this.ParameterSet().numberOfRepetitionsForAverage();
		return average;
	}

	public long minDurationAmongSingleAmongSolves(PointSet pointSet) {
		long minDuration = this.durationOfSingleSolve(pointSet);
		for (int count = 1; count < this.ParameterSet().numberOfRepetitionOfSameExecution(); count++) {
			long duration = this.durationOfSingleSolve(pointSet);
			if (duration < minDuration)
				minDuration = duration;
		}
		return minDuration;
	}

}
