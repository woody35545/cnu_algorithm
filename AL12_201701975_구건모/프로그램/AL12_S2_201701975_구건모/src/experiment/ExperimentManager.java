package experiment;

import app.AppView;

public abstract class ExperimentManager {

	private static final boolean DEBUG_MODE = false;

	private static void showDebugMessage(String aMessage) {
		if (ExperimentManager.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}
	}

	protected static final int DEFUALT_NUMBER_OF_ITERATION = 10;
	protected static final int DEFUALT_INCREMENT_SIZE = 1000;
	protected static final int DEFUALT_STARTING_SIZE = DEFUALT_INCREMENT_SIZE;
	protected static final int DEFUALT_NUMBER_OF_REPITITION_OF_SINGLE_SORT = 1;

	private ExperimentDataSet _dataSet;
	private Experiment _experiment;
	private ParameterSetForMeasurement _parameterSetForMeasurement;

	public ExperimentDataSet dataSet() {
		return this._dataSet;

	}

	protected void setDataSet(ExperimentDataSet newDataSet) {
		this._dataSet = newDataSet;
	}

	protected Experiment experiment() {
		return this._experiment;
	}

	protected void setExperiment(Experiment newExperiment) {
		this._experiment = newExperiment;
	}

	public ParameterSetForMeasurement parameterSetForMeasurement() {
		return this._parameterSetForMeasurement;
	}

	protected void setParameterSetForMeasurement(ParameterSetForMeasurement newParameterSet) {
		this._parameterSetForMeasurement = newParameterSet;
	}

	protected void prepareDataSet() {
		this.setDataSet(new ExperimentDataSet(this.parameterSetForMeasurement().maxDataSize()));
	}

	protected void setParameterSetWithDefualts() {
		this.setParameterSetForMeasurement(new ParameterSetForMeasurement(DEFUALT_STARTING_SIZE,
				DEFUALT_NUMBER_OF_ITERATION, DEFUALT_INCREMENT_SIZE, DEFUALT_NUMBER_OF_REPITITION_OF_SINGLE_SORT));
	}

	protected abstract void performMeasuring(ListOrder anOrder);

	public ExperimentManager() {
		this.setParameterSetWithDefualts();
		showDebugMessage("super - ExperimentManager");
	}

	public void prepareExperiment(ParameterSetForMeasurement aParameterSet) {
		if (aParameterSet != null) {
			this.setParameterSetForMeasurement(aParameterSet);
		}
		this.setExperiment(new Experiment(this.parameterSetForMeasurement()));
		this.prepareDataSet();
		this.performMeasuring(ListOrder.Random);
	}

	public abstract void performExperiment(ListOrder anOrder);

}
