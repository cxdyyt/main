package Heaps;

import java.util.Date;
import java.util.Random;

public class HeapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random(20);
		Heap heap1 = new Heap();
		int mb = 100;
		Integer ints[] = new Integer[mb];
		int i = 0;
		while (i < mb) {
			int next = rand.nextInt(mb*2);
			ints[i]=next;
			heap1.insert(next);
			i++;
		}
		long ms = new Date().getTime();
		
		Heap heap2 = new Heap(ints);
		heap1.printSorted();
		testDecreaseKey(heap1, 17, 2);
		//testDelete(heap1, 4);
		heap1.printSorted();
		long ms1 = new Date().getTime();
		System.out.println("performance: " + (ms1 - ms));
	}
	
	private static boolean testDecreaseKey(Heap heap,int ele,int devalue) {
		heap.decreaseKey(ele, devalue);
		return verifyInvalidHeap(heap);
	}
	
	private static boolean testDelete(Heap heap,int ele) {
		heap.delete(ele);
		return verifyInvalidHeap(heap);
	}
	
	private static boolean verifyInvalidHeap(Heap heap) {
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
