package Entity;

public class NodeLeftist<K extends Comparable<K>> extends Node<K,NodeLeftist<K>>{
	

	// a single new leftist node's zero path length is 0;
	int zeroPathLength = 0;

	
	public int getZeroPathLength() {
		return zeroPathLength;
	}

	public void setZeroPathLength(int zeroPathLength) {
		this.zeroPathLength = zeroPathLength;
	}
}
