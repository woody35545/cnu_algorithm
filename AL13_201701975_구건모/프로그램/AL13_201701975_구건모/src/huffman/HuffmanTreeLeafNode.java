package huffman;

public class HuffmanTreeLeafNode extends HuffmanTreeNode {
	private short _byteCode;

	protected short byteCode() {
		return this._byteCode;
	}

	private void setByteCode(short newByteCode) {
		this._byteCode = newByteCode;
	}

	protected HuffmanTreeLeafNode(short givenByteCode) {
		this.setLeft(null);
		this.setRight(null);
		this.setByteCode(givenByteCode);
	}
}
