package Sorting;

public class SelectionSort<T extends Comparable<T>> extends CommonSort<T> {

    public void sortInner1(T[] arrs) {

        for (int i = 0; i < arrs.length - 1; i++) {
            int minIndex = i;
            for (int j = i; j < arrs.length; j++) {
                T curr = arrs[j];
                if (curr.compareTo(arrs[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T min = arrs[minIndex];
            arrs[minIndex] = arrs[i];
            arrs[i] = min;
        }

    }

    public void sortInner(T[] arrs) {
        T temp = null;
        for (int i = 0; i <= arrs.length - 2; i++) {
            int indexMin = i;
            for (int j = i + 1; j <= arrs.length - 1; j++) {
                if (arrs[j].compareTo(arrs[indexMin]) < 0) {
                    indexMin = j;
                }
            }
            temp = arrs[i];
            arrs[i] = arrs[indexMin];
            arrs[indexMin] = temp;
        }

    }
}
