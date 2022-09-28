package graph;

public class UndirectedAdjacencyMatrixGraph<E extends Edge> extends DirectedAdjacencyMatrixGraph<E> {
	public UndirectedAdjacencyMatrixGraph(int givenNumberOfVertices) {
		super(givenNumberOfVertices);
	}

	public boolean addEdge(E anEdge) {
		if (this.edgeIsValid(anEdge) && (!this.edgeDoesExist(anEdge))) {
			int tailVertex = anEdge.tailVertex();
			int headVertex = anEdge.headVertex();

			this.setAdjacencyOfEdgeAsExist(tailVertex, headVertex);
			this.setAdjacencyOfEdgeAsExist(headVertex, tailVertex);
			this.setNumberOfEdges(this.numberOfEdges() + 1);
			return true;
		}
		return false;
	}

}
