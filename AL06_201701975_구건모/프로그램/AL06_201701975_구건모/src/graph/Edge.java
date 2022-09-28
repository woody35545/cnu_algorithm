package graph;


public class Edge { /* Edge는 tailVertex와 headVertex로 구성되므로 각각 private 접근자를 이용해 선언 */
	private int _tailVertex; /* 외부에서 직접 접근 할 수 없도록 private으로 선언, 외부에서는 getter/setter를 통해 접근 */
	private int _headVertex; 

	public Edge(int givenTailVertex, int givenHeadVertex) { /* Edge 생성자 선언, 두 개의 vertex를 인자로 받아 생성됨 */ 
		this._tailVertex = givenTailVertex;
		this._headVertex = givenHeadVertex;
	}

	public void setTailVertex(int newTailVertex) { 
		this._tailVertex = newTailVertex;
	}

	public int tailVertex() {
		return this._tailVertex;
	}

	public void setHeadVertex(int newHeadVertex) {
		this._headVertex = newHeadVertex;
	}

	public int headVertex() {
		return this._headVertex;

	}

}
