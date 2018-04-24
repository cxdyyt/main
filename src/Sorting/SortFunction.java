package Sorting;

public interface SortFunction<T extends Comparable<T>> {
	void sort(T[] arrs);
	void printList();
}
