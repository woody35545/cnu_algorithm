package graph;

import list.Iterator;

public class WeightedDirectedAdjacencyListGraph<WE extends WeightedEdge> extends DirectedAdjacencyListGraph<WE>
		implements SupplementForWeightedGraph<WE> {

	private static final int WEIGHT_INFINITE = Integer.MAX_VALUE / 2;

	public WeightedDirectedAdjacencyListGraph(int givenNumberOfVertices) {
		super(givenNumberOfVertices);

	}

	protected int adjacencyOfEdge(int aTailVertex, int aHeadVertex) {
		Iterator<WE> iterator = (Iterator<WE>) this.neighborIteratorOf(aTailVertex);
		while (iterator.hasNext()) {
			WE neighborEdge = (WE) iterator.next();
			if (aHeadVertex == neighborEdge.headVertex()) {
				return neighborEdge.weight();

			}
		}
		return WeightedDirectedAdjacencyListGraph.WEIGHT_INFINITE;
	}

	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		if (this.edgeIsValid(aTailVertex, aHeadVertex)) {
			return (this.adjacencyOfEdge(aTailVertex,
					aHeadVertex) < WeightedDirectedAdjacencyListGraph.WEIGHT_INFINITE);

		}
		return false;

	}

	public int weightOfEdge(int aTailVertex, int aHeadVertex) {
		if (this.edgeIsValid(aTailVertex, aHeadVertex)) {
			return this.adjacencyOfEdge(aTailVertex, aHeadVertex);

		}
		return WeightedDirectedAdjacencyListGraph.WEIGHT_INFINITE;
	}

	public int weightOfEdge(WE anEdge) {
		if (this.edgeIsValid(anEdge)) {
			return this.adjacencyOfEdge(anEdge.tailVertex(), anEdge.headVertex());
		}

		return WeightedDirectedAdjacencyListGraph.WEIGHT_INFINITE;
	}
}
