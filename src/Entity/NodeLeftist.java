package Entity;

public class NodeLeftist<T> extends Node<T>{
	int zeroPathLength = 0;

	public NodeLeftist(T item) {
		super(item);
	}
	
	public int getZeroPathLength() {
		return zeroPathLength;
	}

	public void setZeroPathLength(int zeroPathLength) {
		this.zeroPathLength = zeroPathLength;
	}
}
