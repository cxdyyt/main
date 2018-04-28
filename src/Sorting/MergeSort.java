package Sorting;

import InterFaces.SortFunction;

/**
 * @see SortFunction
 * @author Beck
 * @see BubbleSort,CommonSort
 * @param <T>
 */
public class MergeSort<T extends Comparable<T>> extends CommonSort<T> {
	T[] globalTmp = null;
	@Override
	public void sortInner(T[] arrs) {
		int len = arrs.length;
		globalTmp = (T[]) new Comparable[len];
		megeSort(arrs, 0, len - 1);
	}

	private void megeSort(T[] arrs,int start,int end) {
		// only one item
		if(end == start) {
			globalTmp[start] = arrs[start];
		}else if(end - start == 1){
			// two items
			int comp = arrs[end].compareTo(arrs[start]);
			if(comp < 0) {
				globalTmp[start] = arrs[end];
				globalTmp[end] = arrs[start];
			}else {
				globalTmp[start] = arrs[start];
				globalTmp[end] = arrs[end];
			}
		}else {
			// more than two items
			int midd = (start+end)/2;
			megeSort(arrs,start,midd);
			megeSort(arrs,midd+1,end);
			int pl = midd;
			int pr = end;
			int i=start,j=midd+1,m=start;
			while(i<=pl || j<=pr) {
				T litem = i > pl ? null : arrs[i];
				T ritem = j > pr ? null : arrs[j];
				if(litem == null) {
					if(ritem != null) {
						// litem is null and ritem is not null
						globalTmp[m++] = ritem;
						j++;
					}
				}else {
					if(ritem == null) {
						// ritem is null and litem is not null
						globalTmp[m++] = litem;
						i++;
					}else {
						// ritem is not null and litem is not null
						if(litem.compareTo(ritem) > 0) {
							globalTmp[m++] = ritem;
							j++;
						}else {
							globalTmp[m++] = litem;
							i++;
						}
					}
				}
			}
		}
		System.arraycopy(globalTmp,start,arrs,start,end - start + 1);
	}
}
