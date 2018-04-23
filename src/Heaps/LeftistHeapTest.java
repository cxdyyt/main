package Heaps;

import java.util.Random;

public class LeftistHeapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random(20);
		int mb = 20;
		int i = 0;
		LeftistHeap<Integer> heaps = new LeftistHeap<>();
		while (i < mb) {
			int next = rand.nextInt(mb*3);
			heaps.insert(next);
			System.out.print(next+",");
			i++;
		}
		System.out.println();
		heaps.printSorted();
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
