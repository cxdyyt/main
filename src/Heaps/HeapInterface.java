package Heaps;

public interface HeapInterface<T> {
	public void printSorted();
	T removeFirst();
	void insert(T ele);
}
