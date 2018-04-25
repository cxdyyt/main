package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Heaps.BinaryHeap;
import Heaps.LeftistHeap;
import InterFaces.HeapInterface;
import InterFaces.SortFunction;
import Sorting.BubbleSort;
import Sorting.HeapSort;
import Sorting.InsertionSort;
import Sorting.SelectionSort;
import Sorting.ShellSort;

public class SortingTest {
	public static void testBubbleSort(Integer[] arrs) {
		BubbleSort<Integer> bubbleSort = new BubbleSort<Integer>();
		bubbleSort.sort(arrs);
		bubbleSort.printList();
	}
	public static void testSelectionSort(Integer[] arrs) {
		SelectionSort<Integer> selectionSort = new SelectionSort<Integer>();
		selectionSort.sort(arrs);
		selectionSort.printList();
	}
	public static void testInsertionSort(Integer[] arrs) {
		InsertionSort<Integer> InsertionSort = new InsertionSort<Integer>();
		InsertionSort.sort(arrs);
		InsertionSort.printList();
	}
	public static void testShellSort(Integer[] arrs) {
		ShellSort<Integer> ShellSort = new ShellSort<Integer>();
		ShellSort.sort(arrs);
		ShellSort.printList();
	}

	
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
		
		HeapInterface<Integer> heap = new LeftistHeap<Integer>();
		HeapInterface<Integer> heap1 = new BinaryHeap();
		sorts.add(new HeapSort<Integer>(heap ));
		sorts.add(new HeapSort<Integer>(heap1 ));
		testServeralSorting(sorts , false,20000);
	}
}
