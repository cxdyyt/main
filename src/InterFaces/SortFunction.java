package InterFaces;
/**
 * 
 * @author Beck
 * @see 
 * @param <T>
 */
public interface SortFunction<T extends Comparable<T>> extends Comparable<SortFunction>  {
	void sort(T[] arrs);
	void printList();
	long getExecuTime();
}
