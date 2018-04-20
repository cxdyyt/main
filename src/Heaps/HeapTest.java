package Heaps;

import java.util.Date;
import java.util.Random;

public class HeapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
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
		heap2.printSorted();
		
		long ms1 = new Date().getTime();
		System.out.println("performance: " + (ms1 - ms));
		verifyInvalidHeap(heap1);
	}
	
	private static void verifyInvalidHeap(Heap heap) {
		for (int j = 1; j<=heap.heaps.length/2; j++) {
			if (heap.heaps[j] == null) {
				continue;
			}
			if (heap.heaps[j * 2] != null) {
				if (heap.heaps[j] > heap.heaps[j * 2]) {
					System.out.println(j + "," + (j * 2));
				}
			}

			if (heap.heaps[j * 2 + 1] != null) {
				if (heap.heaps[j] > heap.heaps[j * 2 + 1]) {
					System.out.println(j + "," + (j * 2 + 1));
				}
			}
		}
	}

}
