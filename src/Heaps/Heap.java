package Heaps;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Heap {
	long times = 0;
	Integer[] heaps;
	int lastLeft = 0;
	int lastPostion = 0;

	public Heap(Integer[] nums) {
		super();
		int len = nums.length;
		lastPostion = len;
		if (len % 2 == 0) {
			lastLeft = len;
		} else {
			lastLeft = len / 2 + 1;
		}
		this.heaps = new Integer[2 * (len + 1)];
		System.arraycopy(nums, 0, heaps, 1, len);
		sortHeao(1);
	}

	public void insert(int ele) {
		int parent = lastPostion / 2;
		int testPosition = lastPostion;
		while (parent > 0 && heaps[parent] > ele) {
			heaps[testPosition] = heaps[parent];
			testPosition = parent;
			parent = parent / 2;
		}
		heaps[testPosition] = ele;
	}

	public int removeFirst() {
		int targetPostion = 1;
		int testleftPosition = 0;
		int lastValue = heaps[lastPostion];
		int result = heaps[1];
		while (targetPostion <= lastPostion/2) {
			int upValue = 0;
			int upFrom = 0;
			testleftPosition = 2 * targetPostion;
			if(testleftPosition == lastPostion || (heaps[testleftPosition] <= heaps[testleftPosition +1])) {
				upFrom = testleftPosition;
				upValue = heaps[testleftPosition];
			}else {
				upFrom = testleftPosition + 1;
				upValue = heaps[testleftPosition+1];
			}
			if(lastValue <= upValue) {
				heaps[targetPostion] = lastValue;
				break;
			}else {
				heaps[targetPostion] = upValue;
				if(upFrom > lastPostion /2) {
					heaps[upFrom] = lastValue;
					break;
				}
				targetPostion = upFrom;
			}
		}
		heaps[lastPostion] = null;
		lastPostion--;
		return result;
	}

	public void sortHeao(int i) {
		if (i >= lastLeft) {
			return;
		}
		times++;
		if (heaps[i] != null) {
			int changePostion = 0;
			int small = 0;
			if (heaps[2 * i] != null) {
				sortHeao(2 * i);
			}
			if (heaps[2 * i + 1] != null) {
				sortHeao(2 * i + 1);
			}
			if (heaps[2 * i] == null && heaps[2 * i + 1] == null) {
				return;
			}
			if (heaps[2 * i] != null) {
				if (heaps[2 * i + 1] != null) {
					if (heaps[2 * i] > heaps[2 * i + 1]) {
						small = heaps[2 * i + 1];
						changePostion = 2 * i + 1;
					} else {
						small = heaps[2 * i];
						changePostion = 2 * i;
					}
				} else {
					small = heaps[2 * i];
					changePostion = 2 * i;
				}
			} else {
				small = heaps[2 * i + 1];
				changePostion = 2 * i + 1;
			}

			if (heaps[i] > small) {
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
		Integer ints[] = new Integer[1500];
		int i = 0;
		while (i < 1500) {
			ints[i] = rand.nextInt(2000);
			i++;
		}
		// heap.buildHeao(new Integer[] { 95,11, 65, 72, 13,7});
		long ms = new Date().getTime();
		Heap heap = new Heap(ints);
		long ms1 = new Date().getTime();
		heap.insert(0);
		System.out.println("performance: " + (ms1 - ms));
		System.out.println("times: " + heap.times);
		System.out.println("sys1: " + Arrays.toString(heap.heaps));
		while(heap.lastPostion >0) {
			System.out.println(heap.removeFirst());
		}
		for (int j = 1; j < heap.heaps.length; j++) {
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
