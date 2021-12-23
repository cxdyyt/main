package Sorting;

import java.util.Arrays;

public class ShellSort<T extends Comparable<T>> extends CommonSort<T> {

    // @Override
    public void sortInner1(T[] arrs) {
        int len = arrs.length;
        int ham = len / 2;
        while (ham > 0) {
            for (int j = ham; j < len; j++) {
                T curr = arrs[j];
                for (int k = j; k >= 0; k = k - ham) {
                    int preInd = k - ham;
                    if (preInd < 0) {
                        arrs[k] = curr;
                        break;
                    } else {
                        T pre = arrs[preInd];
                        if (pre.compareTo(curr) > 0) {
                            arrs[k] = pre;
                        } else {
                            arrs[k] = curr;
                            break;
                        }
                    }
                }
            }
            ham /= 2;
        }
    }

    public void sortInner(T[] arrs) {
        for (int gap = arrs.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i <= arrs.length - 1; i += gap) {
                T temp = arrs[i];
                int j = i - gap;
                for (; j >= 0 && temp.compareTo(arrs[j]) < 0; j -= gap) {
                    arrs[j + gap] = arrs[j];
                }

                arrs[j + gap] = temp;
            }
        }
    }

    public static void main(String args[]) {
        // Integer[] inns = new Integer[] { 4, 6, 8, 10, 3, 7, 2, 5, 1, 9 };
        Integer[] inns = new Integer[] { 8, 10, 3 };
        new ShellSort<Integer>().sortInner(inns);
        System.out.println(Arrays.asList(inns));
    }
}
