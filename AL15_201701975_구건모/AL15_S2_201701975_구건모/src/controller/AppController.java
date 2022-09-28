package controller;

import experiment.ExperimentManager;
import experiment.ParameterSet;
import view.AppView;

public class AppController {
	private static final int STARTING_SIZE = 1000;
	private static final int NUMBER_OF_STEPS = 10;
	private static final int INCREMENT_SIZE = 1000;
	private static final int NUMBER_OF_REPETITIONS_OF_SAME_EXECUTION = 50;
	private static final int MIN_RECURSIVE_SIZE = 200;
	private ExperimentManager _experimentManager;
	private ParameterSet _parameterSet;

	private ExperimentManager experimentManager() {
		return this._experimentManager;

	}

	private void setExperimentManager(ExperimentManager newExperimentManager) {
		this._experimentManager = newExperimentManager;
	}

	private ParameterSet parameterSet() {
		return this._parameterSet;
	}

	private void setParameterSet(ParameterSet newParameterSet) {
		this._parameterSet = newParameterSet;
	}

	public AppController() {
		this.setParameterSet(new ParameterSet(AppController.NUMBER_OF_STEPS, AppController.STARTING_SIZE,
				AppController.INCREMENT_SIZE, AppController.NUMBER_OF_REPETITIONS_OF_SAME_EXECUTION,
				AppController.MIN_RECURSIVE_SIZE)

		);
		this.setExperimentManager(new ExperimentManager(this.parameterSet()));

	}

	private void showMeasurement(long[][] measurement, String measurementTitle) {
		AppView.output("");
		AppView.outputLine("<" + measurementTitle + "측정> (단위: 마이크로 초)");
		AppView.outputLine(String.format("%8s %20s %20s %20s %12s", "Size ", "Compare-All-Pairs", "Divide-And-Conquer",
				"Hybrid(MinRS: " + MIN_RECURSIVE_SIZE + ")", "Saving (%)"));
		int size = this.parameterSet().startingSize();
		for (int step = 0; step < this.parameterSet().numberOfSteps(); step++) {
			long durationByComparingAllPairs = measurement[0][step];
			long durationByDivideAndConquer = measurement[1][step];
			long durationByHybrid = measurement[2][step];
			double savingRatioByHybrid = (double) (durationByDivideAndConquer - durationByHybrid) * 100.0
					/ (double) durationByDivideAndConquer;
			AppView.outputLine(String.format("[%6d]%20d %20d %20d %12.0f", size, durationByComparingAllPairs,
					durationByDivideAndConquer, durationByHybrid, savingRatioByHybrid));
			size += this.parameterSet().incrementSize();

		}
	}

	public void run() {
		AppView.outputLine("");
		AppView.outputLine("<<< 최단거리 쌍 찾기 성능 측정을 시작합니다 >>>");
		if (this.experimentManager().closestPairAlgorithmAreCorrect()) {
			long[][] measurementOfSingleSolve = this.experimentManager().measureAverageDurationOfSingleSolves();
			measurementOfSingleSolve = this.experimentManager().measureAverageDurationOfSingleSolves();
			this.showMeasurement(measurementOfSingleSolve, "한번 실행");
			long[][] measurementOfAverageOfSingleSolves = this.experimentManager().measureDurationOfSingleSolve();
			this.showMeasurement(measurementOfAverageOfSingleSolves, "반복 실행의 평균");
			long[][] measurementOfMinOfSingleSolves = this.experimentManager()
					.measurementMinDurationAmongSingleSolves();
			this.showMeasurement(measurementOfMinOfSingleSolves, "반복 실행 중 최소 시간");
		} else {
			AppView.outputLine("! 현재 구현되어 있는 알고리즘이 올바로 작동하지 않습니다.");
		}
		AppView.outputLine("");
		AppView.outputLine("<<< 최단거리 쌍 찾기 성능 측정을 종료합니다 >>>");
	}
}
