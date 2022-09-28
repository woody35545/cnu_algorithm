
public class AdjacencyMatrixGraph {
	private static final int EDGE_EXIST = 1; /* Edge ���翩�θ� �ľ��ϱ� ���� ������ ����, Edge�� �������� �ǹ� */
	private static final int EDGE_NONE = 0; /* Edge�� ���� ��츦 ��Ÿ���� ���� ���� */
	private int _numberOfVertices; /* �� Vertex �� */
	private int _numberOfEdges; /* �� Edge �� */
	private int[][] _adjacency; /* adjacency ������ ���� matrix */

	public AdjacencyMatrixGraph(int givenNumberOfVerices) {
		this.setNumberOfVertices(givenNumberOfVerices); /* */
		this.setNumberOfEdges(0); /* ���ʿ��� Edge�� �����Ƿ� 0���� �ʱ�ȭ */
		this.setAdjacency(new int[givenNumberOfVerices][givenNumberOfVerices]);
		for (int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {
			for (int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++) {
				this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_NONE; /*
																							 * �ʱ� Matrix�� Edge�� �����Ƿ� ���
																							 * element���� EDGE_NONE�� �̿��Ͽ�
																							 * 0���� �ʱ�ȭ
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
				.numberOfVertices()); /* vertex�� 0�� ���ų� ũ�� numberOfVertices���� ���� ���̾�� ��. �̿��� ��� �������� ����. */

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
					&& this.vertexDoesExist(headVertex)) { /* �Է����� ���� edge�� tail�� head vertex�� �����ϴ� ��쿡�� ���� */
				if (!this.adjacencyOfEdgeDoesExist(tailVertex, headVertex)) { /* �������� ���� ��쿡�� �߰� */
					this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_EXIST; /*
																								 * Undirected Matrix
																								 * Graph �̹Ƿ� tail,head ��
																								 * ���� �߰��Ǹ� head,tail��
																								 * ��쵵 �߰��Ǿ�� ��.(�� ��츦 ����
																								 * �ٸ��� �������� ����)
																								 */
					this.adjacency()[headVertex][tailVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
					this.setNumberOfEdges(
							this.numberOfEdges() + 1); /* edge�� �߰��Ǿ����Ƿ� setter�� �̿��� numberOfEdges ���� �ϳ� �÷��� */
					return true;

				}
			}
		}
		return false;
	}

}
