
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
			AppView.output("? Edge 수를 입력하시오: ");
			try {
				int numberOfEdges = AppView.inputInt();
				if (numberOfEdges < 0) {
					AppView.outputLine("[오류] Edge 수는 0 보다 크거나 같아야 합니다."); /* edge 는 0개일 수도 있음 */
				} else {
					return numberOfEdges;
				}
			} catch (NumberFormatException e) {
				AppView.outputLine("[오류] 올바른 숫자가 입력되지 않았습니다.");
			}

		}
	}

	private int inputNumberOfVertices() {
		while (true) {
			AppView.output("? Vertex 수를 입력하시오: ");
			try {
				int numberOfVertices = AppView.inputInt();
				if (numberOfVertices < 1) {
					AppView.outputLine("[오류] Vertex 수는 0 보다 커야 합니다."); /* vertex 가 없는 그래프는 다루지 않음 */
				} else {
					return numberOfVertices;
				}
			} catch (NumberFormatException e) {
				AppView.outputLine("[오류] 올바른 숫자가 입력되지 않았습니다.");
			}

		}
	}

	private Edge inputEdge() {
		do {
			AppView.outputLine("- 입력할 edge의 두 vertex를 차례로 입력해야 합니다: ");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 vertex 번호가 동일합니다."); /* self-loop Edge가 추가되는 것을 방지 */
				} else {
					return (new Edge(tailVertex, headVertex));
				}

			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 tail vertex 입니다: " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 head vertex 입니다: " + headVertex);

				}

			}
		} while (true);

	}

	private void inputAndMakeGraph() {
		AppView.outputLine("> 입력할 그래프의 vertex 수와 edge 수를 먼저 입력해야 합니다: ");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new AdjacencyMatrixGraph(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 edge를 주어진 수 만큼 입력합니다.");
		this.initCycleDetection();
		int edgeCount = 0;
		while (edgeCount < numberOfEdges) { /* 현재까지 추가된 edge 개수인 edgeCount가 numberOfEdges가 되기전까지만 수행 */
			Edge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"(오류) 입력된 edge (" + edge.tailVertex() + "," + edge.headVertex() + ") 는 그래프에 이미 존재합니다.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 edge (" + edge.tailVertex() + "," + edge.headVertex() + ") 가 그래프에 삽입되었습니다.");
				if (this.addEdgeDoesMakeCycle(edge)) {
					AppView.outputLine("![Cycle] 삽입된 edge (" + edge.tailVertex() + "," + edge.headVertex()
							+ ") 는 그래프에 사이클을 만들었습니다.");
				}
			}
		}

	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> 입력된 그래프는 다음과 같습니다: ");
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

		AppView.outputLine("<<< 입력되는 그래프의 사이클 검사를 시작합니다 >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		AppView.outputLine("");
		AppView.outputLine("<<< 그래프의 입력과 사이클 검사를 종료합니다 >>>");

	}
}
