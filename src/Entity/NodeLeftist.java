package Entity;

public class NodeLeftist<K extends Comparable<K>> extends Node<K,NodeLeftist<K>>{
	

	int zeroPathLength = 0;

	
	public int getZeroPathLength() {
		return zeroPathLength;
	}

	public void setZeroPathLength(int zeroPathLength) {
		this.zeroPathLength = zeroPathLength;
	}
}
