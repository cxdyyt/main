package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AVL<K> {

	Node<Comparable<K>> top;

	public Node<Comparable<K>> push(Comparable<K> item) {
		if (top == null) {
			top = new Node<Comparable<K>>();
		}
		Node<Comparable<K>> res = push(top, item);
		return top;
	}

	public Comparable<K> get(Comparable<K> item) {
		Comparable<K> res = getItems(top, item);
		return res;
	}

	public void printlist() {
		printlistZX(top);
	}

	private void printlist(Node<Comparable<K>> beg) {
		if (beg == null) {
			return;
		} else {
			Comparable<K> item = beg.getItem();
			Node<Comparable<K>> left = beg.getLeft();
			Node<Comparable<K>> right = beg.getRight();
			printlist(left);
			System.out.println(item + ",");
			printlist(right);
		}
	}

	private void printlistZX(Node<Comparable<K>> beg) {
		List<Comparable<K>> result = new ArrayList<Comparable<K>>();
		LinkedList<Node> listQuence = new LinkedList<Node>();
		Node<Comparable<K>> handNode = beg;
		Node<Comparable<K>> previousCom = null;
		while (handNode != null) {
			Node<Comparable<K>> left = handNode.getLeft();
			Node<Comparable<K>> right = handNode.getRight();
			if (previousCom != null) {
				if (previousCom == left) {
					result.add(handNode.getItem());
					previousCom =handNode; 
					handNode = listQuence.poll();
					continue;
				}else if(previousCom.getRight() == handNode) {
					if (right != null) {
						listQuence.push(right);
					}
					if (left != null) {
						listQuence.push(handNode);
						handNode = left;
					} else {
						result.add(handNode.getItem());
						previousCom = handNode;
						handNode = listQuence.poll();
					}
				
				}else {
					result.add(handNode.getItem());
					previousCom = handNode;
					handNode = listQuence.poll();
				}
			}else {
				if (right != null) {
					listQuence.push(right);
				}
				if (left != null) {
					listQuence.push(handNode);
					handNode = left;
				} else {
					result.add(handNode.getItem());
					previousCom = handNode;
					handNode = listQuence.poll();
				}
			}

		}
		for (Comparable<K> com : result) {
			System.out.println(com);
		}
	}

	private void printlistHX(Node<Comparable<K>> beg) {
		List<Comparable<K>> result = new ArrayList<Comparable<K>>();
		LinkedList<Node> listQuence = new LinkedList<Node>();
		Node<Comparable<K>> handNode = beg;
		Node<Comparable<K>> previousCom = null;
		while (handNode != null) {
			Node<Comparable<K>> left = handNode.getLeft();
			Node<Comparable<K>> right = handNode.getRight();
			if (previousCom != null) {
				if (left == previousCom || right == previousCom) {
					result.add(handNode.getItem());
					previousCom = handNode;
					handNode = listQuence.poll();
					continue;
				}
			}
			if (left == null && right == null) {
				result.add(handNode.getItem());
				previousCom = handNode;
				handNode = listQuence.poll();
			} else {
				listQuence.push(handNode);
				if (left != null) {
					handNode = left;
					if (right != null) {
						listQuence.push(right);
					}
				}else {
					if(right != null) {
						handNode = right;
					}
				}
			}
		}
		for (Comparable<K> com : result) {
			System.out.println(com);
		}
	}

	private Comparable<K> getItems(Node<Comparable<K>> par, Comparable item) {
		if (par == null) {
			return null;
		} else {
			Comparable<K> res = null;
			Comparable<K> paritem = par.getItem();
			Node<Comparable<K>> left = par.getLeft();
			Node<Comparable<K>> right = par.getRight();
			int comp = item.compareTo(paritem);
			if (comp == 0) {
				return item;
			} else {
				if (comp > 0) {
					res = getItems(right, item);
				} else {
					res = getItems(left, item);
				}
			}
			return res;
		}
	}

	private Node<Comparable<K>> push(Node<Comparable<K>> parent, Comparable item) {
		if (parent.getItem() == null) {
			parent.setItem(item);
			return parent;
		} else {
			Node<Comparable<K>> res;
			Comparable<K> parentItem = parent.getItem();
			Node<Comparable<K>> left = parent.getLeft();
			Node<Comparable<K>> right = parent.getRight();
			int compare = item.compareTo(parentItem);
			if (compare == 0) {
				res = parent;
			} else if (compare > 0) {
				if (right == null) {
					right = new Node<Comparable<K>>();
					parent.setRight(right);
				}
				res = push(right, item);
				parent.setHeight(res.getHeight() + 1);
			} else {
				if (left == null) {
					left = new Node<Comparable<K>>();
					parent.setLeft(left);
				}
				res = push(left, item);
				parent.setHeight(res.getHeight() + 1);
			}
			return parent;
		}
	}

	private void ensurenaAVL(Node<Comparable<K>> node, Comparable added) {
		Node<Comparable<K>> left = node.getLeft();
		Node<Comparable<K>> right = node.getRight();
		if (Math.abs(left.getHeight() - right.getHeight()) > 1) {

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rang = new Random(47);
		AVL<Integer> avl = new AVL<Integer>();
		int i =0;
		while(i<9) {
			i++;
			int tmp = rang.nextInt(100);
			System.out.println(tmp);
			avl.push(tmp);
		}
		avl.printlist();
		System.out.println(avl);
	}

	private static class Node<T> {
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
}
