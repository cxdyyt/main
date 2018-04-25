package Sorting;

import InterFaces.HeapInterface;

public class HeapSort<T extends Comparable<T>> extends CommonSort<T> {

	HeapInterface<T> heap;
	public HeapSort(HeapInterface<T> heap) {
		super();
		this.heap = heap;
	}


	@Override
	protected void sortInner(T[] arrs) {
		System.out.println("heap sort using [" + heap.getClass().getSimpleName() + "]");
		heap.insert(arrs);
		int i = 0;
		while (heap.hasMore()) {
			arrs[i] = heap.removeFirst();
			i++;
		}
		super.arrs = arrs;
	}

}
