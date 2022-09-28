package app;

import EquivalanceClasses.EquivalanceClasses;
import graph.AdjacencyGraph;
import graph.DirectedAdjacencyListGraph;
import graph.Edge;
import graph.UndirectedAdjacencyListGraph;
import list.Iterator;
import list.List;

public class AppController {

	private AdjacencyGraph<Edge> _graph;
	private EquivalanceClasses<Edge> _equivalanceClasses;

	public AppController() {
		this.setGraph(null);
		this.setEquivalanceClasses(new EquivalanceClasses<Edge>());
	}

	private AdjacencyGraph<Edge> graph() {
		return this._graph;
	}

	private void setGraph(AdjacencyGraph<Edge> newGraph) {
		this._graph = newGraph;
	}

	private EquivalanceClasses<Edge> equivalanceClasses() {
		return this._equivalanceClasses;
	}

	private void setEquivalanceClasses(EquivalanceClasses<Edge> newEquivalanceClasses) {
		this._equivalanceClasses = newEquivalanceClasses;
	}

	private int inputNumberOfEdges() {
		int numberOfEdges = AppView.inputNumberOfEdges();
		while (numberOfEdges < 0) {
			AppView.outputLine("[오류] 관계쌍의 개수는 0 보다 크거나 같아야 합니다: " + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfEdges();

		}
		return numberOfEdges;
	}

	private int inputNumberOfVertices() {
		int numberOfVertices = AppView.inputNumberOfVertices();
		while (numberOfVertices <= 0) {
			AppView.outputLine("[오류] 원소의 개수는 0 보다 커야 합니다: " + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();

		}
		return numberOfVertices;
	}

	private Edge inputEdge() {
		do {
			AppView.outputLine("- 입력할 관계 쌍의 두 원소를 차례로 입력해야 합니다.");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 원소가 동일합니다."); /* self-loop Edge가 추가되는 것을 방지 */
				} else {
					return (new Edge(tailVertex, headVertex));
				}

			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[오류] 1번째 입력값은 존재하지 않는 원소입니다: " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 2번째 입력값은 존재하지 않는 원소입니다: " + headVertex);
				}

			}
		} while (true);

	}

	private void inputAndMakeGraph() {
		AppView.outputLine("> 입력할 동등 관계의 원소의 수와 관계 쌍의 수를 먼저 입력해야 합니다." + " ");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new UndirectedAdjacencyListGraph<Edge>(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 관계 쌍을 주어진 수 만큼 입력합니다.");
		int edgeCount = 0;
		while (edgeCount < numberOfEdges) { /* 현재까지 추가된 edge 개수인 edgeCount가 numberOfEdges가 되기전까지만 수행 */
			Edge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine(
						"(오류) 입력된 관계쌍 (" + edge.tailVertex() + "," + edge.headVertex() + ") 는 그래프에 이미 존재합니다.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 관계 쌍 (" + edge.tailVertex() + "," + edge.headVertex() + ") 가 그래프에 삽입되었습니다.");

			}
		}

	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> 입력된 관계 쌍의 그래프는 다음과 같습니다: ");

		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {

			AppView.output("[" + tailVertex + "] ->");
			Iterator<Edge> neighborIterator = this.graph().neighborIteratorOf(tailVertex);
			while (neighborIterator.hasNext()) {
				Edge nextEdge = neighborIterator.next();
				AppView.output(" " + nextEdge.headVertex());
			}

			AppView.outputLine("");
		}
		AppView.outputLine("");

	}

	private void showEquivalanceClasses() {
		AppView.outputLine("");
		AppView.outputLine("> 찾아진 동등 클래스는 다음과 같습니다.");
		Iterator<List<Integer>> equivalanceClassListIterator = this.equivalanceClasses().equivalanceClassList()
				.listIterator();
		for (int classOrder = 0; equivalanceClassListIterator.hasNext(); classOrder++) {
			AppView.output("[동등 클래스 " + String.format("%2d", classOrder) + "] ");

			List<Integer> equivalanceClass = (List<Integer>) equivalanceClassListIterator.next();
			Iterator<Integer> equivalanceClassIterator = equivalanceClass.listIterator();
			AppView.output(String.format(" {%2d", equivalanceClassIterator.next()));

			while (equivalanceClassIterator.hasNext()) {
				AppView.output(String.format(" ,%2d", equivalanceClassIterator.next()));

			}
			AppView.outputLine(" }");

		}

	}

	public void run() {

		AppView.outputLine("<<< 동등 클래스 찾기 찾기 프로그램을 시작합니다 >>>");
		this.inputAndMakeGraph();
		this.showGraph();

		if (this.equivalanceClasses().solve(this.graph())) {
			this.showEquivalanceClasses();

		} else {
			AppView.outputLine("");
			AppView.outputLine("[오류] 동등 클래스를 성공적으로 마치지 못했습니다. ");
		}

		AppView.outputLine("");
		AppView.outputLine("<<< 동등 클래스 찾기 프로그램을 종료합니다 >>>");

	}
}
