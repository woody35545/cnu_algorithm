
public class AdjacencyMatrixGraph {
	private static final int EDGE_EXIST = 1; /* Edge 존재여부를 파악하기 위해 선언한 변수, Edge가 존재함을 의미 */
	private static final int EDGE_NONE = 0; /* Edge가 없을 경우를 나타내기 위한 변수 */
	private int _numberOfVertices; /* 총 Vertex 수 */
	private int _numberOfEdges; /* 총 Edge 수 */
	private int[][] _adjacency; /* adjacency 정보를 담을 matrix */

	public AdjacencyMatrixGraph(int givenNumberOfVerices) {
		this.setNumberOfVertices(givenNumberOfVerices); /* */
		this.setNumberOfEdges(0); /* 최초에는 Edge가 없으므로 0으로 초기화 */
		this.setAdjacency(new int[givenNumberOfVerices][givenNumberOfVerices]);
		for (int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {
			for (int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++) {
				this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_NONE; /*
																							 * 초기 Matrix는 Edge가 없으므로 모든
																							 * element들을 EDGE_NONE을 이용하여
																							 * 0으로 초기화
																							 */
			}
		}
	}

	private void setNumberOfVertices(int newNumberOfVertices) {
		this._numberOfVertices = newNumberOfVertices;
	}

	private void setNumberOfEdges(int newNumberOfEdges) {
		this._numberOfEdges = newNumberOfEdges;
	}

	private int[][] adjacency() {
		return this._adjacency;
	}

	private void setAdjacency(int[][] newAdjacency) {
		this._adjacency = newAdjacency;
	}

	private boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {
		return (this.adjacency()[tailVertex][headVertex] != AdjacencyMatrixGraph.EDGE_NONE);
	}

	public int numberOfVertices() {
		return this._numberOfVertices;
	}

	public int numberOfEdges() {
		return this._numberOfEdges;
	}

	public boolean vertexDoesExist(int aVertex) {
		return (aVertex >= 0 && aVertex < this
				.numberOfVertices()); /* vertex는 0과 같거나 크고 numberOfVertices보다 작은 값이어야 함. 이외의 경우 존재하지 않음. */

	}

	public boolean edgeDoesExist(Edge anEdge) {
		if (anEdge != null) {
			int tailVertex = anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			if (this.vertexDoesExist(tailVertex) && this.vertexDoesExist(headVertex)) {
				return (this.adjacencyOfEdgeDoesExist(tailVertex, headVertex));
			}
		}
		return false;
	}

	public boolean addEdge(Edge anEdge) {
		if (anEdge != null) {
			int tailVertex = anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			if (this.vertexDoesExist(tailVertex)
					&& this.vertexDoesExist(headVertex)) { /* 입력으로 들어온 edge의 tail과 head vertex가 존재하는 경우에만 진행 */
				if (!this.adjacencyOfEdgeDoesExist(tailVertex, headVertex)) { /* 존재하지 않을 경우에만 추가 */
					this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_EXIST; /*
																								 * Undirected Matrix
																								 * Graph 이므로 tail,head 한
																								 * 쌍이 추가되면 head,tail의
																								 * 경우도 추가되어야 함.(두 경우를 서로
																								 * 다르게 구분하지 않음)
																								 */
					this.adjacency()[headVertex][tailVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
					this.setNumberOfEdges(
							this.numberOfEdges() + 1); /* edge가 추가되었으므로 setter를 이용해 numberOfEdges 값을 하나 올려줌 */
					return true;

				}
			}
		}
		return false;
	}

}
