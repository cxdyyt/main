package Sorting;

import java.util.Random;

public class SortingTest {
	private static int leng = 101;
	public int getLeng() {
		return leng;
	}
	public void setLeng(int leng) {
		this.leng = leng;
	}
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
	public static void main(String[] args) {
		//testBubbleSort(gernerateItems());
		//testSelectionSort(gernerateItems());
		testInsertionSort(gernerateItems());
	}
	
	private static Integer[] gernerateItems() {
		Integer[] arrs = new Integer[leng];
		Random ran = new Random(37);
		for(int i=0;i<leng;i++) {
			arrs[i] = ran.nextInt(leng*3);
		}
		return arrs;
	}

}
