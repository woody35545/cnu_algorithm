package experiment;

import model.FindClosestPair;
import model.PointSet;

public class ExperientForDivideAndConquer extends Experiment {

	protected ExperientForDivideAndConquer(FindClosestPair givenFindClosestPair,
			ParameterSet givenParameterSet) {
		super(givenFindClosestPair, givenParameterSet);
		// TODO Auto-generated constructor stub
	}

	public long durationOfSingleSolve(PointSet pointSet) {
		Timer.start();
		this.findClosestPair().solveByDivideAndConquer(pointSet);
		Timer.stop();
		return Timer.duration();
	}

}
