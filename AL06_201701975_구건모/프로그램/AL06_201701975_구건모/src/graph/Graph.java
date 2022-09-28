package graph;

public interface Graph<E> {
	public int numberOfVertices();

	public int numberOfEdges();

	public boolean vertexDoesExist(int aVertex);

	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex);

	public boolean edgeDoesExist(E anEdge);

	public boolean edgeIsValid(int aTailVertex, int aHeadVertex);

	public boolean edgeIsValid(E anEdge);

	public E edge(int aTailVertex, int aHeadVertex);

	public boolean addEdge(E anEdge);

}
