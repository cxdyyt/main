package Sorting;

public class InsertionSort<T extends Comparable<T>> extends CommonSort<T> {

	@Override
	protected void sortInner(T[] arrs) {
		sortInner(arrs, 0, arrs.length - 1);
	}
	protected void sortInner(T[] arrs,int start,int end) {
		for (int i = start + 1; i <= end; i++) {
			T curr = arrs[i];
			for (int j = i; j >=0; j--) {
				T pre = null;
				if(j == 0) {
					arrs[j] = curr;
					break;
				}else {
					pre = arrs[j-1];
					if(pre.compareTo(curr) <= 0) {
						arrs[j] = curr;
						break;
					}else {
						arrs[j] = pre;
					}
				}
			}
		}
	}

}
