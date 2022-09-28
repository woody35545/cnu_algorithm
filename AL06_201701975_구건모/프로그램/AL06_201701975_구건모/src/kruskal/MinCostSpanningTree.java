package kruskal;

import graph.WeightedUndirectedAdjacencyMatrixGraph;
import app.AppView;
import graph.WeightedEdge;
import list.LinkedList;
import list.List;

public class MinCostSpanningTree {
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> _graph;
	private MinPriorityQ<WeightedEdge> _minPriorityQ;
	private List<WeightedEdge> _spanningTreeEdgeList;

	public MinCostSpanningTree() {
		this.setGraph(null);
		this.setMinPriorityQ(null);
		this.setSpanningTreeEdgeList(null);
	}

	private void initMinPriorityQ() {
		this.setMinPriorityQ(new MinPriorityQ<WeightedEdge>(this.graph().numberOfEdges()));
		int numberOfVertices = this.graph().numberOfVertices();

		for (int tailVertex = 0; tailVertex < numberOfVertices; tailVertex++) {
			for (int headVertex = tailVertex + 1; headVertex < numberOfVertices; headVertex++) {
				if (this.graph().edgeDoesExist(tailVertex, headVertex)) {
					int weight = this.graph().weightOfEdge(tailVertex, headVertex);
					WeightedEdge edge = new WeightedEdge(tailVertex, headVertex, weight);
					this.minPriorityQ().add(edge);
				}
			}
		}
	}

	public List<WeightedEdge> solve(WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> aGraph) {
		this.setGraph(aGraph);
		this.initMinPriorityQ();
		this.setSpanningTreeEdgeList(new LinkedList<WeightedEdge>());

		PairwiseDisjointSets pairwiseDisjointSets = new PairwiseDisjointSets(this.graph().numberOfVertices());

		int maxNumberOfTreeEdges = this.graph().numberOfVertices() - 1;

		while ((this.spanningTreeEdgeList().size() < maxNumberOfTreeEdges) && (!this.minPriorityQ().isEmpty())) {

			WeightedEdge edge = this.minPriorityQ().removeMin();
			int setOfTailVertex = pairwiseDisjointSets.find(edge.tailVertex());
			int setOfHeadVertex = pairwiseDisjointSets.find(edge.headVertex());
			if (setOfTailVertex == setOfHeadVertex) {
				AppView.outputLine("[DEBUG] Edge(" + edge.tailVertex() + ", " + edge.headVertex() + ", ("
						+ edge.weight() + "))�� ���д� Ʈ���� ����Ŭ�� ������Ű�Ƿ�, �����ϴ�.");
			} else {
				this.spanningTreeEdgeList().add(edge);
				pairwiseDisjointSets.union(setOfTailVertex, setOfHeadVertex);
				AppView.outputLine("[DEBUG] Edge(" + edge.tailVertex() + ", " + edge.headVertex() + ", ("
						+ edge.weight() + "))�� ���д� Ʈ���� edge�� �߰��˴ϴ�.");
			}
		}

		return (this.spanningTreeEdgeList().size() == maxNumberOfTreeEdges) ? this.spanningTreeEdgeList() : null;
	}

	private MinPriorityQ<WeightedEdge> minPriorityQ() {
		return this._minPriorityQ;
	}

	private void setMinPriorityQ(MinPriorityQ<WeightedEdge> newMinPriorityQ) {
		this._minPriorityQ = newMinPriorityQ;
	}

	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> graph() {
		return this._graph;
	}

	private void setGraph(WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> newGraph) {
		this._graph = newGraph;
	}

	private List<WeightedEdge> spanningTreeEdgeList() {
		return this._spanningTreeEdgeList;
	}

	private void setSpanningTreeEdgeList(List<WeightedEdge> newSpanningTreeEdgeList) {
		this._spanningTreeEdgeList = newSpanningTreeEdgeList;
	}
}
