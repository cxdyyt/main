package InterFaces;

public interface HeapInterface<T extends Comparable<T>> {
	public void printSorted();
	T removeFirst();
	void insert(T ele);;
	void insert(T[] ele);
	boolean hasMore();
}
