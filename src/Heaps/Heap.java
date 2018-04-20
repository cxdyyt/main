package Heaps;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Heap {
	Integer[] heaps;
	
	public Heap(Integer[] nums) {
		super();
		this.heaps = new Integer[2*(nums.length+1)];
		System.arraycopy(nums, 0, heaps, 1, nums.length);
		sortHeao(1);
	}
	public void sortHeao(int i) {
		if(heaps[i] != null) {
			int changePostion = 0;
			int small = 0;
			if(heaps[2*i] != null) {
				sortHeao(2*i);
			}
			if(heaps[2*i+1] != null) {
				sortHeao(2*i+1);
			}
			if(heaps[2*i] == null && heaps[2*i+1] == null) {
				return;
			} 
			if(heaps[2*i] != null) {
				if(heaps[2*i+1] != null) {
					if(heaps[2*i] > heaps[2*i+1]) {
						small = heaps[2*i+1];
						changePostion = 2*i+1;
					}else {
						small = heaps[2*i];
						changePostion = 2*i;
					}
				}else {
					small = heaps[2*i];
					changePostion = 2*i;
				}
			}else {
				small = heaps[2*i+1];
				changePostion = 2*i+1;
			}

			if(heaps[i] > small) {
				int tmp = heaps[i];
				heaps[i] = small;
				heaps[changePostion] = tmp;
				sortHeao(changePostion);
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		Integer ints[] = new Integer[100000];
		int i=0;
		while(i<100000) {
			ints[i] = rand.nextInt(100000);
			i++;
		}
		//heap.buildHeao(new Integer[] { 95,11,  65, 72, 13,7});
		long ms = new Date().getTime();
		Heap heap = new Heap(ints);
		long ms1 = new Date().getTime();
		System.out.println("performance: " + (ms1-ms));
		//System.out.println("sys1: " + Arrays.toString(heap.heaps));
		for(int j=1;j<heap.heaps.length;j++) {
			if(heap.heaps[j] == null) {
				continue;
			}
			if(heap.heaps[j*2] != null) {
				if(heap.heaps[j] > heap.heaps[j*2]) {
					System.out.println(j+","+(j*2));
				}
			}

			if(heap.heaps[j*2+1] != null) {
				if(heap.heaps[j] > heap.heaps[j*2+1]) {
					System.out.println(j+","+(j*2+1));
				}
			}
		}
	}

}
