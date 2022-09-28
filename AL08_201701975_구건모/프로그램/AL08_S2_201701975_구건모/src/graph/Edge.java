package graph;


public class Edge { /* Edge�� tailVertex�� headVertex�� �����ǹǷ� ���� private �����ڸ� �̿��� ���� */
	private int _tailVertex; /* �ܺο��� ���� ���� �� �� ������ private���� ����, �ܺο����� getter/setter�� ���� ���� */
	private int _headVertex; 

	public Edge(int givenTailVertex, int givenHeadVertex) { /* Edge ������ ����, �� ���� vertex�� ���ڷ� �޾� ������ */ 
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
