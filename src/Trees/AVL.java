package Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Entity.NodeAVL;

public class AVL<K extends Comparable<K>> {

	NodeAVL<K> top;

	public NodeAVL<K> push(K item) {
		if (top == null) {
			top = new NodeAVL<K>();
		}
		NodeAVL<K> res = push(top, item);
		return top;
	}

	public Comparable<K> get(Comparable<K> item) {
		Comparable<K> res = getItems(top, item);
		return res;
	}

	public void printlist() {
		printlistZX(top);
	}

	private void printlist(NodeAVL<K> beg) {
		if (beg == null) {
			return;
		} else {
			Comparable<K> item = beg.getItem();
			NodeAVL<K> left = beg.getLeft();
			NodeAVL<K> right = beg.getRight();
			printlist(left);
			System.out.println(item + ",");
			printlist(right);
		}
	}

	private void printlistZX(NodeAVL<K> beg) {
		List<Comparable<K>> result = new ArrayList<Comparable<K>>();
		LinkedList<NodeAVL<K>> listQuence = new LinkedList<NodeAVL<K>>();
		NodeAVL<K> handNode = beg;
		boolean isForward = true;
		System.out.println("-------------------------------------------");
		System.out.print("[");
		while (handNode != null) {
			NodeAVL<K> left = handNode.getLeft();
			NodeAVL<K> right = handNode.getRight();
			if (isForward) {
				if(left != null) {
					listQuence.push(handNode);
					handNode = left;
					isForward = true;
				}else {
					result.add(handNode.getItem());
					System.out.print(handNode.getItem()+", ");
					if(right != null) {
						handNode = right;
						isForward = true;
					}else {
						handNode = listQuence.poll();
						isForward = false;
					}
				}
			}else {
				result.add(handNode.getItem());
				System.out.print(handNode.getItem()+", ");
				if(right != null) {
					handNode = right;
					isForward = true;
				}else {
					handNode = listQuence.poll();
					isForward = false;
				}
			}

		}
		System.out.println("]");
	}

	private void printlistHX(NodeAVL<K> beg) {
		List<Comparable<K>> result = new ArrayList<Comparable<K>>();
		LinkedList<NodeAVL<K>> listQuence = new LinkedList<NodeAVL<K>>();
		NodeAVL<K> handNode = beg;
		NodeAVL<K> previousCom = null;
		while (handNode != null) {
			NodeAVL<K> left = handNode.getLeft();
			NodeAVL<K> right = handNode.getRight();
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

	private Comparable<K> getItems(NodeAVL<K> par, Comparable item) {
		if (par == null) {
			return null;
		} else {
			Comparable<K> res = null;
			Comparable<K> paritem = par.getItem();
			NodeAVL<K> left = par.getLeft();
			NodeAVL<K> right = par.getRight();
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

	private NodeAVL<K> push(NodeAVL<K> parent, K item) {
		if (parent.getItem() == null) {
			parent.setItem(item);
			return parent;
		} else {
			NodeAVL<K> res;
			K parentItem = parent.getItem();
			NodeAVL<K> left = parent.getLeft();
			NodeAVL<K> right = parent.getRight();
			int compare = item.compareTo(parentItem);
			if (compare == 0) {
				res = parent;
			} else if (compare > 0) {
				if (right == null) {
					right = new NodeAVL<K>();
					parent.setRight(right);
				}
				res = push(right, item);
				parent.setHeight(res.getHeight() + 1);
			} else {
				if (left == null) {
					left = new NodeAVL<K>();
					parent.setLeft(left);
				}
				res = push(left, item);
				parent.setHeight(res.getHeight() + 1);
			}
			return parent;
		}
	}

	private void ensurenaAVL(NodeAVL<K> node, Comparable added) {
		NodeAVL<K> left = node.getLeft();
		NodeAVL<K> right = node.getRight();
		if (Math.abs(left.getHeight() - right.getHeight()) > 1) {

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rang = new Random(47);
		List<Integer> list = new ArrayList<Integer>();
		AVL<Integer> avl = new AVL<Integer>();
		int i =0;
		while(i<9) {
			i++;
			int tmp = rang.nextInt(100);
			list.add(tmp);
			avl.push(tmp);
		}
		Collections.sort(list);
		System.out.println("original items");
		System.out.println("------------------------------");
		System.out.println(list);
		System.out.println("tree items");
		avl.printlist();
	}

}
