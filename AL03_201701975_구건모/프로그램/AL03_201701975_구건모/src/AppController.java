
public class AppController {
	private AdjacencyMatrixGraph _graph;
	private PairwiseDisjointSets _pairwiseDisjointSets;

	public AppController() {
		this.setGraph(null);
		this.setPairwiseDisjointSets(null);
	}

	private PairwiseDisjointSets pairwiseDisjointSets() {
		return this._pairwiseDisjointSets;
	}

	private void setPairwiseDisjointSets(PairwiseDisjointSets newPairwiseDisjointSets) {
		this._pairwiseDisjointSets = newPairwiseDisjointSets;
	}

	
	private boolean addEdgeDoesMakeCycle(Edge anAddedEdge) {
		int tailVertex = anAddedEdge.tailVertex();
		int headVertex = anAddedEdge.headVertex();
		int setForTailVertex = this.pairwiseDisjointSets().find(tailVertex);
		int setForHeadVertex = this.pairwiseDisjointSets().find(headVertex);
		if (setForTailVertex == setForHeadVertex) {
			return true;

		} else {
			this.pairwiseDisjointSets().union(setForTailVertex, setForHeadVertex);
			return false;
		}
	}

	
	private void initCycleDetection() {
		this.setPairwiseDisjointSets(new PairwiseDisjointSets(this.graph().numberOfVertices()));
	}

	private AdjacencyMatrixGraph graph() {
		return this._graph;

	}

	private void setGraph(AdjacencyMatrixGraph newGraph) {
		this._graph = newGraph;

	}


	private int inputNumberOfEdges() {
		while (true) {
			AppView.output("? Edge ���� �Է��Ͻÿ�: ");
			try {
				int numberOfEdges = AppView.inputInt();
				if (numberOfEdges < 0) {
					AppView.outputLine("[����] Edge ���� 0 ���� ũ�ų� ���ƾ� �մϴ�."); /* edge �� 0���� ���� ���� */
				} else {
					return numberOfEdges;
				}
			} catch (NumberFormatException e) {
				AppView.outputLine("[����] �ùٸ� ���ڰ� �Էµ��� �ʾҽ��ϴ�.");
			}

		}
	}

	private int inputNumberOfVertices() {
		while (true) {
			AppView.output("? Vertex ���� �Է��Ͻÿ�: ");
			try {
				int numberOfVertices = AppView.inputInt();
				if (numberOfVertices < 1) {
					AppView.outputLine("[����] Vertex ���� 0 ���� Ŀ�� �մϴ�."); /* vertex �� ���� �׷����� �ٷ��� ���� */
				} else {
					return numberOfVertices;
				}
			} catch (NumberFormatException e) {
				AppView.outputLine("[����] �ùٸ� ���ڰ� �Էµ��� �ʾҽ��ϴ�.");
			}

		}
	}

	private Edge inputEdge() {
		do {
			AppView.outputLine("- �Է��� edge�� �� vertex�� ���ʷ� �Է��ؾ� �մϴ�: ");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[����] �� vertex ��ȣ�� �����մϴ�."); /* self-loop Edge�� �߰��Ǵ� ���� ���� */
				} else {
					return (new Edge(tailVertex, headVertex));
				}

			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[����] �������� �ʴ� tail vertex �Դϴ�: " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[����] �������� �ʴ� head vertex �Դϴ�: " + headVertex);

				}

			}
		} while (true);

	}

	private void inputAndMakeGraph() {
		AppView.outputLine("> �Է��� �׷����� vertex ���� edge ���� ���� �Է��ؾ� �մϴ�: ");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new AdjacencyMatrixGraph(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine("> �������� edge�� �־��� �� ��ŭ �Է��մϴ�.");
		this.initCycleDetection();
		int edgeCount = 0;
		while (edgeCount < numberOfEdges) { /* ������� �߰��� edge ������ edgeCount�� numberOfEdges�� �Ǳ��������� ���� */
			Edge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"(����) �Էµ� edge (" + edge.tailVertex() + "," + edge.headVertex() + ") �� �׷����� �̹� �����մϴ�.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!���ο� edge (" + edge.tailVertex() + "," + edge.headVertex() + ") �� �׷����� ���ԵǾ����ϴ�.");
				if (this.addEdgeDoesMakeCycle(edge)) {
					AppView.outputLine("![Cycle] ���Ե� edge (" + edge.tailVertex() + "," + edge.headVertex()
							+ ") �� �׷����� ����Ŭ�� ��������ϴ�.");
				}
			}
		}

	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� ������ �����ϴ�: ");
		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" + tailVertex + "] ->");
			for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				if (this.graph().edgeDoesExist(new Edge(tailVertex, headVertex))) {
					AppView.output(" " + headVertex);
				}
			}
			AppView.outputLine("");
		}
	}

	public void run() {

		AppView.outputLine("<<< �ԷµǴ� �׷����� ����Ŭ �˻縦 �����մϴ� >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		AppView.outputLine("");
		AppView.outputLine("<<< �׷����� �Է°� ����Ŭ �˻縦 �����մϴ� >>>");

	}
}
