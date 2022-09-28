package graph;

public class WeightedUndirectedAdjacencyMatrixGraph<WE extends WeightedEdge> extends UndirectedAdjacencyMatrixGraph<WE>
		implements SupplementForWeightedGraph<WE> {
	private static final int WEIGHTED_EDGE_NONE = -1;

	public WeightedUndirectedAdjacencyMatrixGraph(int givenNumberOfVertices) {
		super();
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);

		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for (int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {
			for (int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++) {
				this.setWeightedOfEdgeAsNone(tailVertex, headVertex);
			}
		}
	}

	private void setWeightOfEdge(int aTailVertex, int aHeadVertex, int newWeight) {
		this.adjacency()[aTailVertex][aHeadVertex] = newWeight;
	}

	private void setWeightedOfEdgeAsNone(int aTailVertex, int aHeadVertex) {
		this.setWeightOfEdge(aTailVertex, aHeadVertex, WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE);
	}

	protected boolean adjacencyOfEdgeDoesExist(int aTailVertex, int aHeadVertex) {

		return (this.adjacencyOfEdge(aTailVertex,
				aHeadVertex) != WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE);
	}

	public int weightOfEdge(WE anEdge) {
		if (anEdge != null) {
			return this.weightOfEdge(anEdge.tailVertex(), anEdge.headVertex());
		}
		return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
	}

	public int weightOfEdge(int aTailVertex, int aHeadVertex) {
		if (this.edgeDoesExist(aTailVertex, aHeadVertex)) {
			return this.adjacencyOfEdge(aTailVertex, aHeadVertex);

		}
		return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
	}

	public boolean addEdge(WE anEdge) {
		if (anEdge != null) {
			if (this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)) {
				int tailVertex = anEdge.tailVertex();
				int headVertex = anEdge.headVertex();
				this.setWeightOfEdge(tailVertex, headVertex, anEdge.weight());
				this.setWeightOfEdge(headVertex, tailVertex, anEdge.weight());
				this.setNumberOfEdges(this.numberOfEdges() + 1);
				return true;
			}

		}
		return false;
	}
}
