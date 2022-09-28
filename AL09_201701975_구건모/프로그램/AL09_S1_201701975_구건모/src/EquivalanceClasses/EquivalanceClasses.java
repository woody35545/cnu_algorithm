package EquivalanceClasses;

import app.AppView;
import graph.AdjacencyGraph;
import graph.Edge;
import list.List;
import list.LinkedList;
import list.Stack;
import list.ArrayStack;
import list.Iterator;

public class EquivalanceClasses<E extends Edge> {

	private static final boolean DEBUG_MODE = true;

	private AdjacencyGraph<E> _graph;
	private boolean[] _found;
	private Stack<Integer> _sameClassMembers;
	private List<List<Integer>> _equivalanceClassList;

	public EquivalanceClasses() {
		this.setGraph(null);
		this.setFound(null);
		this.setSameClassMembers(null);
		this.setEquivalanceClassList(null);
	}

	private AdjacencyGraph<E> graph() {
		return this._graph;
	}

	private void setGraph(AdjacencyGraph<E> newGraph) {
		this._graph = newGraph;

	}

	private boolean[] found() {
		return this._found;
	}

	private void setFound(boolean[] newFound) {
		this._found = newFound;
	}

	private Stack<Integer> sameClassMembers() {
		return this._sameClassMembers;

	}

	private void setSameClassMembers(ArrayStack<Integer> newSameClassMembers) {
		this._sameClassMembers = newSameClassMembers;
	}

	public List<List<Integer>> equivalanceClassList() {
		return this._equivalanceClassList;

	}

	private void setEquivalanceClassList(List<List<Integer>> newEquivalanceClassList) {
		this._equivalanceClassList = newEquivalanceClassList;
	}

	private static void showDebugMessage(String aMessage) {
		if (EquivalanceClasses.DEBUG_MODE) {
			AppView.outputDebugMessage(aMessage);
		}

	}

	public boolean solve(AdjacencyGraph<E> aGraph) {
		this.setGraph(aGraph);
		if (this.graph().numberOfVertices() < 1) {
			return false;
		}
		this.setFound(new boolean[this.graph().numberOfVertices()]);
		this.setEquivalanceClassList(new LinkedList<List<Integer>>());
		this.setSameClassMembers(new ArrayStack<Integer>());
		EquivalanceClasses.showDebugMessage("\n");
		for (int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++) {
			if (!this.found()[vertex]) {
				EquivalanceClasses.showDebugMessage("[Debug] 새로운 동등 클래스: {");
				List<Integer> newEquivalanceClass = new LinkedList<Integer>();
				this.equivalanceClassList().add(newEquivalanceClass);

				this.found()[vertex] = true;
				newEquivalanceClass.add(vertex);
				EquivalanceClasses.showDebugMessage(" "+vertex);
				this.sameClassMembers().push(vertex);
	
				while (!this.sameClassMembers().isEmpty()) {
					int tailVertex = this.sameClassMembers().pop();
					Iterator<E> iterator = this.graph().neighborIteratorOf(tailVertex);
					while (iterator.hasNext()) {
						E edge = iterator.next();
						int headVertex = edge.headVertex();
						if (!this.found()[headVertex]) {
							this.found()[headVertex] = true;
							newEquivalanceClass.add(headVertex);
							EquivalanceClasses.showDebugMessage(", " + headVertex);
							this.sameClassMembers().push(headVertex);
						}
					}
				}
				EquivalanceClasses.showDebugMessage(" }\n");
			}

		}
		return true;
	}

}
