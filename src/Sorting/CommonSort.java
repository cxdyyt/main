package Sorting;

import java.util.Date;

public abstract class CommonSort<T extends Comparable<T>> implements SortFunction<T> {

	T[] arrs;
	@Override
	public void sort(T[] arrs) {
		this.arrs = arrs;
		long begingTime = new Date().getTime();
		sortInner(arrs);
		long endTime = new Date().getTime();
		System.out.println(this.getClass().getSimpleName() + " performance is: [" + (endTime - begingTime)  + "]");
	};

	protected abstract  void sortInner(T[] arrs);
	
	@Override
	public void printList() {
		if(arrs == null) {
			return;
		}
		System.out.println(this.getClass().getSimpleName());
		System.out.println("---------------------------------------------");
		System.out.print("[");
		for(T t : arrs) {
			System.out.print(t+ ", ");
		}
		System.out.print("]");
		System.out.println("---------------------------------------------");
	}

}
