package Entity;


public class Node<K,T extends Node<K,T>> {
	K item;
	T left;
	T right;
	public K getItem() {
		return item;
	}
	public void setItem(K item) {
		this.item = item;
	}
	public T getLeft() {
		return left;
	}
	public void setLeft(T left) {
		this.left = left;
	}
	public T getRight() {
		return right;
	}
	public void setRight(T right) {
		this.right = right;
	}

}
