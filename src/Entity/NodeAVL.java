package Entity;

public class NodeAVL<K extends Comparable<K>> extends Node<K, NodeAVL<K>> {

	int height = 0;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
