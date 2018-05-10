package Sorting;

import InterFaces.SortFunction;

/**
 * @see SortFunction
 * @author Beck
 * @see BubbleSort,CommonSort
 * @param <T>
 */
public class QuickSortWithMin<T extends Comparable<T>> extends QuickSort<T> {
	int min = 10;
	
	@Override
	public void sortInner(T[] arrs) {
		subSortWithMin(arrs, 0, arrs.length-1);
	}

	private void subSortWithMin(T[] arrs,int start,int end) {
		if(start + min > end) {
			InsertionSort inst = new InsertionSort<T>();
			inst.sortInner(arrs, start, end);
			return;
		}
		T flag = threeMiddle(arrs, start, end);
		int i = start + 1;
		int j = end - 2;
		while(i <= j) {
			T ti = arrs[i];
			T tj = arrs[j];
			if(ti.compareTo(flag) < 0) {
				i++;
			}
			if(tj.compareTo(flag) > 0) {
				j--;
			}
			if(ti.compareTo(flag) >= 0 && tj.compareTo(flag) <=0) {
				swap(arrs, i, j);
				i++;
				j--;
			}
		}
		swap(arrs, i, end - 1);
		subSortWithMin(arrs, start, i-1);
		subSortWithMin(arrs, i+1, end);
	}

	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
}
