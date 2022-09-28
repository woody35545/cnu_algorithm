package experiment;

import model.FindClosestPair;
import model.PairOfPoints;
import model.Point;
import model.PointSet;

import java.util.Random;

import experiment.ExperientForDivideAndConquer;
import experiment.ExperimentForComparingAllPairs;

public class ExperimentManager {
	private static final int MAX_COORDINATE_VALUE = 1000000;
	private static final int NUMBER_OF_KINDS_OF_EXPERIMENTS = 3;
	private static final int SIZE_FOR_CORRECTNESS_TEST = 10000;

	private ParameterSet _parameterSet;
	private FindClosestPair _findClosestPair;
	private Experiment _experimentForComparingAllPairs;
	private Experiment _experimentForDivideAndConquer;
	private Experiment _experimentForHybrid;

	private Experiment experimentForHybrid() {
		return this._experimentForHybrid;
	}

	private void setExperimentForHybrid(Experiment newExperiment) {
		this._experimentForHybrid = newExperiment;
	}

	private ParameterSet parameterSet() {
		return this._parameterSet;
	}

	private void setParameterSet(ParameterSet newParameterSet) {
		this._parameterSet = newParameterSet;
	}

	private FindClosestPair findClosestPair() {
		return this._findClosestPair;
	}

	private void setFindClosestPair(FindClosestPair newFindClosestPair) {
		this._findClosestPair = newFindClosestPair;

	}

	private Experiment experimentForDivideAndConquer() {
		return this._experimentForDivideAndConquer;
	}

	private void setExperimentForDivideAndConquer(Experiment newExperiment) {
		this._experimentForDivideAndConquer = newExperiment;
	}

	private Experiment experimentForComparingAllPairs() {
		return this._experimentForComparingAllPairs;
	}

	private void setExperimentForComparingAllParis(Experiment newExperiment) {
		this._experimentForComparingAllPairs = newExperiment;
	}

	public ExperimentManager(ParameterSet givenParameterSet) {
		this.setParameterSet(givenParameterSet);
		this.setFindClosestPair(new FindClosestPair());
		this.findClosestPair().setMinRecursiveSize(this.parameterSet().minRecursiveSize());
		this.setExperimentForComparingAllParis(
				new ExperimentForComparingAllPairs(this.findClosestPair(), givenParameterSet));
		this.setExperimentForDivideAndConquer(
				new ExperientForDivideAndConquer(this.findClosestPair(), givenParameterSet));
		this.setExperimentForHybrid(new ExperimentForHybrid(this.findClosestPair(), givenParameterSet));
	}

	private PointSet generatePointSet(int size) {
		PointSet pointSet = new PointSet(size);
		Random random = new Random();
		for (int count = 0; count < size; count++) {
			int x = random.nextInt(MAX_COORDINATE_VALUE);
			int y = random.nextInt(MAX_COORDINATE_VALUE);
			Point point = new Point(x, y);
			pointSet.add(point);
		}
		return pointSet;
	}

	public long[][] measureDurationOfSingleSolve() {
		long[][] measurement = new long[NUMBER_OF_KINDS_OF_EXPERIMENTS][this.parameterSet().numberOfSteps()];

		int sizeForStep = this.parameterSet().startingSize();
		for (int step = 0; step < this.parameterSet().numberOfSteps(); step++) {
			PointSet pointSet = this.generatePointSet(sizeForStep);
			measurement[0][step] = this.experimentForComparingAllPairs().durationOfSingleSolve(pointSet);
			measurement[1][step] = this.experimentForDivideAndConquer().durationOfSingleSolve(pointSet);
			measurement[2][step] = this.experimentForHybrid().durationOfSingleSolve(pointSet);

			sizeForStep += this.parameterSet().incrementSize();

		}
		return measurement;

	}

	public long[][] measureAverageDurationOfSingleSolves() {
		long[][] measurement = new long[NUMBER_OF_KINDS_OF_EXPERIMENTS][this.parameterSet().numberOfSteps()];
		int sizeForStep = this.parameterSet().startingSize();
		for (int step = 0; step < this.parameterSet().numberOfSteps(); step++) {
			PointSet pointSet = this.generatePointSet(sizeForStep);
			measurement[0][step] = this.experimentForComparingAllPairs().averageDurationOfSingleSolves(pointSet);
			measurement[1][step] = this.experimentForDivideAndConquer().averageDurationOfSingleSolves(pointSet);
			measurement[2][step] = this.experimentForHybrid().averageDurationOfSingleSolves(pointSet);

			sizeForStep += this.parameterSet().incrementSize();
		}
		return measurement;
	}

	public long[][] measurementMinDurationAmongSingleSolves() {
		long[][] measurement = new long[NUMBER_OF_KINDS_OF_EXPERIMENTS][this.parameterSet().numberOfSteps()];

		int sizeForStep = this.parameterSet().startingSize();
		for (int step = 0; step < this.parameterSet().numberOfSteps(); step++) {
			PointSet pointSet = this.generatePointSet(sizeForStep);
			measurement[0][step] = this.experimentForComparingAllPairs().minDurationAmongSingleAmongSolves(pointSet);
			measurement[1][step] = this.experimentForDivideAndConquer().minDurationAmongSingleAmongSolves(pointSet);
			measurement[2][step] = this.experimentForHybrid().minDurationAmongSingleAmongSolves(pointSet);

			sizeForStep += this.parameterSet().incrementSize();

		}
		return measurement;
	}

	public boolean closestPairAlgorithmAreCorrect() {
		PointSet pointSet = this.generatePointSet(ExperimentManager.SIZE_FOR_CORRECTNESS_TEST);
		PairOfPoints closestPairByComparingAllPairs = this.findClosestPair().solveByComparingAllPairs(pointSet);
		PairOfPoints closestPairByDivdeAndConquer = this.findClosestPair().solveByDivideAndConquer(pointSet);
		PairOfPoints closestPairByHybrid = this.findClosestPair().solveByHybrid(pointSet);
		return ((closestPairByComparingAllPairs.distance() == closestPairByDivdeAndConquer.distance())
				&& (closestPairByComparingAllPairs.distance() == closestPairByHybrid.distance()));

	}

}
