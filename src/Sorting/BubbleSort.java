package Sorting;

import InterFaces.SortFunction;

/**
 * @see SortFunction
 * @author Beck
 * @see BubbleSort,CommonSort
 * @param <T>
 */
public class BubbleSort<T extends Comparable<T>> extends CommonSort<T> {

    @Override
    public void sortInner(T[] arrs) {
        for (int i = arrs.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                T pre = arrs[j];
                T next = arrs[j + 1];
                if (pre.compareTo(next) > 0) {
                    arrs[j] = next;
                    arrs[j + 1] = pre;
                }
            }
        }
    }

    /**
     * @param arrs
     */
    public void sortInner1(T[] arrs) {
        T tempSwap = null;
        for (int i = arrs.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arrs[j].compareTo(arrs[j + 1]) > 0) {
                    tempSwap = arrs[j + 1];
                    arrs[j + 1] = arrs[j];
                    arrs[j] = tempSwap;
                }
            }
        }
    }

    public static void main(String args[]) {
    }

}
