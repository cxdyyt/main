package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Heaps.BinaryHeap;
import Heaps.LeftistHeap;
import InterFaces.HeapInterface;
import InterFaces.SortFunction;
import Sorting.BubbleSort;
import Sorting.BucketSort;
import Sorting.HeapSort;
import Sorting.InsertionSort;
import Sorting.MergeSort;
import Sorting.QuickSort;
import Sorting.SelectionSort;
import Sorting.ShellSort;

public class SortingTest {
	private static void testServeralSorting(List<SortFunction<Integer>> sorts,boolean isPrint,int len) {
		for(SortFunction sort : sorts) {
			sort.sort((gernerateItems(len)));
			if(isPrint) {
				sort.printList();
			}
		}
	}
	
	private static Integer[] gernerateItems(int len) {
		Integer[] arrs = new Integer[len];
		Random ran = new Random(37);
		//System.out.println("original items");
		for(int i=0;i<len;i++) {
			int next= ran.nextInt(len*3);
			arrs[i] = next;
			//System.out.print(next + " ,");
		}
		System.out.println("------------------------------------");
		return arrs;
	}

	
	public static void main(String[] args) {
		List<SortFunction<Integer>> sorts = new ArrayList<SortFunction<Integer>>();
		//sorts.add(new BubbleSort<Integer>());
		//sorts.add(new SelectionSort<Integer>());
		//sorts.add(new InsertionSort<Integer>());
		//sorts.add(new ShellSort<Integer>());
		byte byt = 0;
		HeapInterface<Integer> heap = new LeftistHeap<Integer>();
		HeapInterface<Integer> heap1 = new BinaryHeap();
		//sorts.add(new HeapSort<Integer>(heap ));
		sorts.add(new MergeSort<Integer>());
		//sorts.add(new HeapSort<Integer>(heap1 ));
		//sorts.add(new BucketSort(200*3));
		sorts.add(new QuickSort<Integer>());
		testServeralSorting(sorts , false,100000);
	}
}
