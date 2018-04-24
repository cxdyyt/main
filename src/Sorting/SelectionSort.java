package Sorting;

public class SelectionSort<T extends Comparable<T>> extends CommonSort<T> {

	@Override
	public void sortInner(T[] arrs) {

		for(int i=0;i<arrs.length-1;i++) {
			int minIndex = i;
			for(int j=i;j<arrs.length;j++) {
				T curr = arrs[j];
				if(curr.compareTo(arrs[minIndex]) < 0) {
					minIndex = j;
				}
			}
			T min = arrs[minIndex]; 
			arrs[minIndex] = arrs[i];
			arrs[i] = min;
		}
		
	}
}
