package Sorting;

import java.util.Date;

import InterFaces.SortFunction;

public abstract class CommonSort<T extends Comparable<T>> implements SortFunction<T> {

    T[] arrs;

    long execuTime = 0;

    @Override
    public void sort(T[] arrs) {
        this.arrs = arrs;
        long begingTime = new Date().getTime();
        sortInner(arrs);
        long endTime = new Date().getTime();
        execuTime = endTime - begingTime;
        // System.out.println(this.getClass().getSimpleName() + " performance is: [" + execuTime +
        // "]");
    };

    protected abstract void sortInner(T[] arrs);

    @Override
    public void printList() {
        if (arrs == null) {
            return;
        }
        // System.out.print(getClass().getName() + ": ");
        System.out.print("[");
        for (T t : arrs) {
            System.out.print(t + ", ");
        }
        System.out.print("]");
        System.out.println("---------------------------------------------");
    }

    public int compareTo(SortFunction o) {
        if (execuTime > o.getExecuTime()) {
            return 1;
        } else if (execuTime < o.getExecuTime()) {
            return -1;
        }
        return 0;
    }

    public long getExecuTime() {
        return execuTime;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [execuTime=" + execuTime + "]";
    }

}
