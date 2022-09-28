
public class AppController {
	private AdjacencyMatrixGraph _graph;
	private Coloring _coloring;

	public AppController() {
		this.setGraph(null);
		this.setColoring(null);
	}

	private Coloring coloring() {
		return this._coloring;
	}

	private void setColoring(Coloring aColoring) {
		this._coloring = aColoring;
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

	private void showColoring() {
		AppView.outputLine("");
		AppView.outputLine("> �� vertex�� ĥ���� ������ ������ �����ϴ�: ");
		for (int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++) {
			AppView.outputLine("[" + vertex + "]" + this.coloring().vertexColor(vertex).name());
		}
		AppView.outputLine("");
		AppView.outputLine("> �� �� vertex�� ������ ���� edge���� ������ �����ϴ�: ");
		if (this.coloring().sameColorEdges().size() == 0) {
			AppView.outputLine("!! ��� edge�� �� �� vertex�� ������ �ٸ��ϴ�.");
		} else {
			LinkedList<Edge>.IteratorForLinkedList iterator = this.coloring().sameColorEdges().iterator();
			while (iterator.hasNext()) {
				Edge currentEdge = iterator.next();
				AppView.output("(" + currentEdge.tailVertex() + "," + currentEdge.headVertex() + "):");
				AppView.outputLine(" " + this.coloring().vertexColor(currentEdge.tailVertex()).name());
			}
		}
	}

	public void run() {

		AppView.outputLine("<<< �ԷµǴ� �׷����� ����Ŭ �˻縦 �����մϴ� >>>");
		this.inputAndMakeGraph();
		this.showGraph();

		this.setColoring(new Coloring(this.graph()));
		this.coloring().runColoring();
		this.showColoring();
		AppView.outputLine("");
		AppView.outputLine("<<< �׷����� �Է°� ����Ŭ �˻縦 �����մϴ� >>>");

	}
}
