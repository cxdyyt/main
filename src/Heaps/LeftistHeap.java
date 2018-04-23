package Heaps;

import Entity.NodeLeftist;

public class LeftistHeap<T extends Comparable<T>> implements HeapInterface<T>{
	
	NodeLeftist<T> top;
	
	public LeftistHeap() {
		super();
	}
	public LeftistHeap(NodeLeftist<T> top) {
		super();
		this.top = top;
	}
	
	/**
	 * get and delete the top item which also be the minimum item
	 */
	public T removeFirst() {
		if(top == null) {
			return null;
		}
		T result = null;
		result = top.getItem();
		NodeLeftist<T> tmptop = merge(top.getLeft(), top.getRight());
		this.top = tmptop;
		return result;
	}
	/**
	 * get and print all the item with ascending sequence
	 */
	public void printSorted() {
		System.out.println();
		System.out.print("Sorted list are: [");
		while(top != null) {
			System.out.print(removeFirst()+",");
		}
		System.out.print("]");
		System.out.println();
	}
	/**
	 * insert an item to heap
	 */
	public void insert(T t) {
		if(t == null) {
			return;
		}
		if(top == null) {
			top = new NodeLeftist<T>();
			top.setItem(t);
			return;
		}
		NodeLeftist<T> addNode = new NodeLeftist<T>();
		addNode.setItem(t);
		this.setTop(merge(top, addNode));
	}
	/**
	 * merger a leftist heap to current this heap
	 * @param heap
	 * @return a merged heap which's top node is either the top of heap or current top
	 */
	public LeftistHeap<T> merge(LeftistHeap<T> heap) {
		if(heap == null || heap.getTop() == null) {
			return this;
		}
		if(top == null) {
			return heap;
		}
		NodeLeftist<T> result = merge(top, heap.getTop());
		this.setTop(result);
		return this;
	}
	
	private NodeLeftist<T> merge(NodeLeftist<T> node1,NodeLeftist<T> node2){
		if(node1 == null && node2 == null) {
			return null;
		}
		if(node1 == null) {
			if(node2.getRight() == null) {
				node2.setZeroPathLength(0);
			}
			return node2;
		}
		if(node2 == null) {
			if(node1.getRight() == null) {
				node1.setZeroPathLength(0);
			}
			return node1;
		}
		T item1 = node1.getItem();
		T item2 = node2.getItem();
		NodeLeftist<T> mainNode = null;
		NodeLeftist<T> subNode = null;
		int comp = item1.compareTo(item2);
		if(comp >= 0) {
			mainNode = node1;
			subNode = node2;
		}else {
			mainNode = node2;
			subNode = node1;
		}
		
		NodeLeftist<T> megered = merge(mainNode, subNode.getRight());
		
		int leftZeroHen = subNode.getLeft() == null ? -1 : subNode.getLeft().getZeroPathLength();
		int rightZeroHen = megered == null ? -1 : megered.getZeroPathLength();

		if(rightZeroHen > leftZeroHen) {
			subNode.setRight(subNode.getLeft());
			subNode.setLeft(megered);
		}else {
			subNode.setRight(megered);
		}
		
		int newZeroHen = Math.min(leftZeroHen,rightZeroHen);
		subNode.setZeroPathLength(newZeroHen+1);
		
		return subNode;
	}
	
	public NodeLeftist<T> getTop() {
		return top;
	}
	public void setTop(NodeLeftist<T> top) {
		this.top = top;
	}
	
}
