package test;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import Heaps.BinaryHeap;

public class BinaryHeapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random(20);
		BinaryHeap heap1 = new BinaryHeap();
		int mb = 1000;
		Integer ints[] = new Integer[mb];
		int i = 0;
		while (i < mb) {
			int next = rand.nextInt(mb*2);
			ints[i]=next;
			heap1.insert(next);
			i++;
		}
		long ms = new Date().getTime();
		Arrays.sort(ints);
		BinaryHeap heap2 = new BinaryHeap(ints);
		verifyInvalidHeap(heap2);
		
		System.out.println("the original items are");
		heap2.printSorted();
		System.out.println("--------------------------------------------");
		
		testDecreaseKey(heap1, 15, 199999);
		//testDelete(heap1, 15);
//		long ms1 = new Date().getTime();
//		System.out.println("performance: " + (ms1 - ms));
	}
	
	private static boolean testDecreaseKey(BinaryHeap heap,int ele,int devalue) {
		System.out.println("test DecreaseKey started, decrease [" + ele + "] with value [" + devalue + "]");
		heap.decreaseKey(ele, devalue);
		heap.printSorted();
		System.out.println("test DecreaseKey end");
		System.out.println("--------------------------------------------");
		return verifyInvalidHeap(heap);
	}
	
	private static boolean testDelete(BinaryHeap heap,int ele) {
		System.out.println("test delete started, delete[" + ele + "]");
		heap.delete(ele);
		heap.printSorted();
		System.out.println("test delete end");
		System.out.println("--------------------------------------------");
		return verifyInvalidHeap(heap);
	}
	
	private static boolean verifyInvalidHeap(BinaryHeap heap) {
		Integer[] heaps = heap.getHeaps();
		for (int j = 1; j<=heaps.length/2; j++) {
			if (heap.getHeaps()[j] == null) {
				continue;
			}
			if ((j * 2) < heaps.length && heaps[j * 2] != null) {
				if (heaps[j] > heaps[j * 2]) {
					System.out.println(j + "," + (j * 2));
					return false;
				}
			}

			if ((j * 2 + 1) < heaps.length && heaps[j * 2 + 1] != null) {
				if (heaps[j] > heaps[j * 2 + 1]) {
					System.out.println(j + "," + (j * 2 + 1));
					return false;
				}
			}
		}
		return true;
	}

}
