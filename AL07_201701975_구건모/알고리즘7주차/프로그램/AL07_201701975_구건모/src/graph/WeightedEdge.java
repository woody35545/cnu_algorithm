package graph;

public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
	private static final int DEFAULT_WEIGHT = 0;
	private int _weight;

	public int compareTo(WeightedEdge otherEdge) {
		if (this.weight() < otherEdge.weight()) {
			return -1;

		} else if (this.weight() > otherEdge.weight()) {
			return +1;

		} else {
			return 0;
		}
	}

	public int weight() {
		return this._weight;

	}

	public void setWeight(int newWeight) {
		this._weight = newWeight;
	}

	public WeightedEdge(int givenTailVertex, int givenHeadVertex) {
		super(givenTailVertex, givenHeadVertex);
		this.setWeight(WeightedEdge.DEFAULT_WEIGHT);
	}

	public WeightedEdge(int givenTailVertex, int givenHeadVertex, int givenWeight) {
		super(givenTailVertex, givenHeadVertex);
		this.setWeight(givenWeight);
	}
}
