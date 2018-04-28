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
		T[] tmpres = megeSort(arrs, 0, len-1);
		System.arraycopy(tmpres, 0, arrs, 0, len);
	}

	private T[] megeSort(T[] arrs,int start,int end) {
		T[] result = (T[]) new Comparable[end-start+1];
		if(end - start  == 0) {
			result[0] = arrs[0];
		}else if(end - start == 1){
			int comp = arrs[end].compareTo(arrs[start]);
			if(comp > 0) {
				result[0] = arrs[start];
				result[1] = arrs[end];
			}else {
				result[0] = arrs[end];
				result[1] = arrs[start];
			}
		}else {
			int midd = start+end/2;
			T[] leftres = megeSort(arrs,start,midd);
			T[] rightres = megeSort(arrs,midd+1,end);
			int pl = midd-start;
			int pr = end-midd;
			int i=0,j=0,m=0;
			while(i<=pl&&j<=pr) {
				T litem = leftres[i];
				T ritem = rightres[j];
				if(litem.compareTo(ritem) > 0) {
					result[m++] = ritem;
					j++;
				}else {
					result[m++] = litem;
					i++;
				}
			}
			T[] rearr = null;
			int startre = 0;
			if(i>j) {
				startre = j;
				rearr = rightres;
			}else {
				rearr = leftres;
				startre = i;
			}
			if(rearr != null) {
				for(;startre<m;startre++) {
					result[startre] = rearr[startre];
				}
			}
		}
		return result;
	}
}
