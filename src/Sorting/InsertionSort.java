package Sorting;

public class InsertionSort<T extends Comparable<T>> extends CommonSort<T> {

	@Override
	protected void sortInner(T[] arrs) {
		for(int i=1;i<arrs.length;i++) {
			int j=i;
			T pre = arrs[j-1];
			T curr = arrs[j];
			while( j > 0) {
				if(pre.compareTo(curr) > 0) {
					arrs[j] = pre;
					pre = arrs[--j];
				}
			}
			arrs[j]=curr;
		}
	}

}
