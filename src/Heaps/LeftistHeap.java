package Heaps;

import Entity.NodeLeftist;

public class LeftistHeap<T extends Comparable<T>> {
	
	NodeLeftist<T> top;
	
	public LeftistHeap() {
		super();
	}
	public LeftistHeap(NodeLeftist<T> top) {
		super();
		this.top = top;
	}
	public LeftistHeap<T> push(T t) {
		if(t == null) {
			return this;
		}
		NodeLeftist<T> addNode = new NodeLeftist<T>(t);
		LeftistHeap<T> heap = new LeftistHeap<T>(addNode);
		return merge(heap);
	}
	public LeftistHeap<T> merge(LeftistHeap<T> heap) {
		LeftistHeap<T> result = null;
		if(heap == null || heap.getTop() == null) {
			return this;
		}
		if(top == null) {
			return heap;
		}
		
		return result;
	}
	private NodeLeftist<T> merge(NodeLeftist<T> node1,NodeLeftist<T> node2){
//		T item1 = node1.getItem();
//		T item2 = node2.getItem();
//		int comp = item1.compareTo(item2);
//		if(comp >= 0) {
//			merge(node1.get, node2);
//		}
		return null;
	}
	public NodeLeftist<T> getTop() {
		return top;
	}
	public void setTop(NodeLeftist<T> top) {
		this.top = top;
	}
	
}
