package Sorting;

import InterFaces.SortFunction;

/**
 * @see SortFunction
 * @author Beck
 * @see BubbleSort,CommonSort
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> extends CommonSort<T> {
	T[] globalTmp = null;
	@Override
	public void sortInner(T[] arrs) {
		subSort(arrs, 0, arrs.length-1);
	}
	private void subSort(T[] arrs,int start,int end) {
		T flag = null;
		if(start == end) {
			return;
		}else if(end - start == 1) {
			if(arrs[start].compareTo(arrs[end]) > 0) {
				swap(arrs, start, end);
			}
			return;
		}else if(end - start == 2) {
			flag = threeMiddle(arrs, start, end);
			return;
		}
		flag = threeMiddle(arrs, start, end);
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
		subSort(arrs, start, i-1);
		subSort(arrs, i+1, end);
	}

	protected void swap(T[] arrs,int i,int j) {
		if(i == j) {
			return;
		}
		T tmp = arrs[i];
		arrs[i] = arrs[j];
		arrs[j] = tmp;
	}
	protected T threeMiddle(T[] arrs,int start,int end) {
		int middle = (start+end)/2;
		if(arrs[start].compareTo(arrs[middle]) > 0) {
			swap(arrs, start, middle);
		}
		if(arrs[middle].compareTo(arrs[end]) > 0) {
			swap(arrs, middle, end);
		}
		if(arrs[start].compareTo(arrs[middle]) > 0) {
			swap(arrs, start, middle);
		}
		swap(arrs, middle, end-1);
		return arrs[end-1];
	}

}
