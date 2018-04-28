package Sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

import InterFaces.SortFunction;

/**
 * @see SortFunction
 * @author Beck
 * @see BubbleSort,CommonSort
 * @param <T>
 */
public class MergeSort<T extends Comparable<T>> extends CommonSort<T> {

	@Override
	public void sortInner(T[] arrs) {
		int len = arrs.length;
		T[] tmpres = megeSort(arrs, 0, len - 1);
		System.arraycopy(tmpres, 0, arrs, 0, len);
	}

	private T[] megeSort(T[] arrs,int start,int end) {
		T[] result = (T[]) new Comparable[end-start+1];
		// only one item
		if(end - start  == 0) {
			result[0] = arrs[start];
		}else if(end - start == 1){
			// two items
			int comp = arrs[end].compareTo(arrs[start]);
			if(comp > 0) {
				result[0] = arrs[start];
				result[1] = arrs[end];
			}else {
				result[0] = arrs[end];
				result[1] = arrs[start];
			}
		}else {
			// more than two items
			int midd = (start+end)/2;
			T[] rearr = null;
			T[] leftres = megeSort(arrs,start,midd);
			T[] rightres = megeSort(arrs,midd+1,end);
			int pl = midd-start;
			int pr = end-midd-1;
			int i=0,j=0,m=0;
			while(i<=pl || j<=pr) {
				T litem = i > pl ? null : leftres[i];
				T ritem = j > pr ? null : rightres[j];
				if(litem == null) {
					if(ritem != null) {
						// litem is null and ritem is not null
						result[m++] = ritem;
						j++;
					}
				}else {
					if(ritem == null) {
						// ritem is null and litem is not null
						result[m++] = litem;
						i++;
					}else {
						// ritem is not null and litem is not null
						if(litem.compareTo(ritem) > 0) {
							rearr = leftres;
							result[m++] = ritem;
							j++;
						}else {
							result[m++] = litem;
							rearr = rightres;
							i++;
						}
					}
				}
			}
		}
		return result;
	}
}
