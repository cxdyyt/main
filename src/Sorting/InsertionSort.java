package Sorting;

public class InsertionSort<T extends Comparable<T>> extends CommonSort<T> {

	@Override
	protected void sortInner(T[] arrs) {
		for (int i = 1; i < arrs.length; i++) {
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
