package app;

import graph.WeightedUndirectedAdjacencyMatrixGraph;
import graph.WeightedEdge;

public class AppController {
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> _graph;

	public AppController() {
		this.setGraph(null);
	}

	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> graph() {
		return this._graph;

	}

	private void setGraph(WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> newGraph) {
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

	private WeightedEdge inputEdge() {
		do {
			AppView.outputLine("- �Է��� edge�� �� vertex�� cost�� ���ʷ� �Է��ؾ� �մϴ�:");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			int cost = AppView.inputCost();
			if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if (tailVertex == headVertex) {
					AppView.outputLine("[����] �� vertex ��ȣ�� �����մϴ�."); /* self-loop Edge�� �߰��Ǵ� ���� ���� */
				} else {
					return (new WeightedEdge(tailVertex, headVertex, cost));
				}

			} else {
				if (!this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[����] �������� �ʴ� tail vertex �Դϴ�: " + tailVertex);
				}
				if (!this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[����] �������� �ʴ� head vertex �Դϴ�: " + headVertex);
				}
				if (cost < 0) {
					AppView.outputLine("[����] edge�� ����� ����̾�� �մϴ�: " + cost);
				}

			}
		} while (true);

	}

	private void inputAndMakeGraph() {
		AppView.outputLine("> �Է��� �׷����� vertex ���� edge ���� ���� �Է��ؾ� �մϴ�: ");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge>(numberOfVertices));

		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine("> �������� edge�� �־��� �� ��ŭ �Է��մϴ�.");
		int edgeCount = 0;
		while (edgeCount < numberOfEdges) { /* ������� �߰��� edge ������ edgeCount�� numberOfEdges�� �Ǳ��������� ���� */
			WeightedEdge edge = this.inputEdge();
			if (this.graph().edgeDoesExist(edge)) {
				AppView.outputLine("(����) �Էµ� edge (" + edge.tailVertex() + "," + edge.headVertex() + ", ("
						+ edge.weight() + ")) �� �׷����� �̹� �����մϴ�.");
			} else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!���ο� edge (" + edge.tailVertex() + "," + edge.headVertex() + ", (" + edge.weight()
						+ ")) �� �׷����� ���ԵǾ����ϴ�.");

			}
		}

	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� ������ �����ϴ�: ");

		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" + tailVertex + "] ->");
			for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				if (this.graph().edgeDoesExist(tailVertex, headVertex)) {
					AppView.output(" " + headVertex);
					AppView.output("(" + this.graph().weightOfEdge(tailVertex, headVertex) + ")");

				}
			}
			AppView.outputLine("");
		}
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� Adjacency Matrix ������ �����ϴ�: ");
		AppView.output("        ");

		for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
			AppView.output(String.format(" [%1s]", headVertex));
		}

		AppView.outputLine("");

		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" + tailVertex + "] ->");
			for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				int weight = this.graph().weightOfEdge(tailVertex, headVertex);
				AppView.output(String.format("%4d", weight));
			}
			AppView.outputLine("");
		}
	}

	public void run() {

		AppView.outputLine("<<< �ּҺ�� Ȯ�� Ʈ�� ã�� ���α׷��� �����մϴ� >>>");
		this.inputAndMakeGraph();
		this.showGraph();

		AppView.outputLine("");
		AppView.outputLine("<<< �ּҺ�� Ȯ�� Ʈ�� ã�� ���α׷��� �����մϴ� >>>");

	}
}
