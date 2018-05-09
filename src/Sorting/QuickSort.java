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
	public void sortInner(T[] arrs) {}

}
