package Sorting;

public class BubbleSort<T extends Comparable<T>> extends CommonSort<T> {

	@Override
	public void sortInner(T[] arrs) {
		for(int i=arrs.length-1;i>0;i--) {
			for(int j=0;j<i;j++) {
				T pre = arrs[j];
				T next = arrs[j+1];
				if(pre.compareTo(next)>0) {
					arrs[j] = next;
					arrs[j+1] = pre;
				}
			}
		}
	}

}
