package InterFaces;
/**
 * 
 * @author Beck
 * @see 
 * @param <T>
 */
public interface SortFunction<T extends Comparable<T>> {
	void sort(T[] arrs);
	void printList();
}
