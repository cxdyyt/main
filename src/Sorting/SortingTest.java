package Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	
	private static void testServeralSorting(List<CommonSort> sorts,boolean isPrint,int len) {
		for(CommonSort sort : sorts) {
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
		List<CommonSort> sorts = new ArrayList<CommonSort>();
		//sorts.add(new BubbleSort<Integer>());
		//sorts.add(new SelectionSort<Integer>());
		//sorts.add(new InsertionSort<Integer>());
		sorts.add(new ShellSort<Integer>());
		testServeralSorting(sorts , false,100000);
	}
}
