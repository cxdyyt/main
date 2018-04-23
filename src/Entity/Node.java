package Entity;


public class Node<T> {
	T item;
	Node left;
	Node right;
	int height = 0;

	public Node() {
		super();
	}

	Node(T item, Node left, Node right) {
		this.item = item;
		this.left = left;
		this.right = right;
	}

	Node(T item) {
		this.item = item;
	}

	Node(T item, Node left, Node right, int height) {
		this.item = item;
		this.left = left;
		this.right = right;
		this.height = height;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
