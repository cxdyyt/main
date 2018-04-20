package Heaps;

public class Heap {
	private Integer[] heaps; // items stored in here
	private int lastLeft = 0; // only used this variable when build heap with given items,it point to the last left node
	private int lastPostion = 1; // start with 1;
	public Heap() {
		super();
		// default size is 100 if start with an empty heap,it can become big as need, for example when it is full
		this.heaps = new Integer[100];
	}

	public void delete(int ele) {
		int minValue = heaps[1];
		int num = decreaseKey(ele, Math.abs(ele-minValue)+1);
		while(num-- > 0) {
			removeFirst();
		}
	}
	
	public int decreaseKey(int ele,int devalue) {
		int result=0;
		for(int i=1;i<=lastPostion;i++) {
			int num = heaps[i];
			if(num == ele) {
				result++;
				int targetPostion = i;
				int parent = targetPostion/2;
				int currentValue = num-devalue;
				while(parent != 0 && currentValue < heaps[parent]) {
					heaps[targetPostion] = heaps[parent];
					targetPostion = parent;
					parent = targetPostion/2;
				}
				heaps[targetPostion] = currentValue;
			}
		}
		return result;
	}
	
	public Heap(Integer[] nums) {
		super();
		// make container has double size of income nums
		this.heaps = new Integer[2 * (nums.length + 1)];
		int j =0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i] != null) {
				heaps[++j] = nums[i];
			}
		}
		lastPostion = j;
		if (j % 2 == 0) {
			lastLeft = j;
		} else {
			lastLeft = j / 2 + 1;
		}
		buildHeap(1);
	}

	public void insert(int ele) {
		// if nothing in array
		if(lastPostion == 1 && heaps[lastPostion] == null) {
			heaps[lastPostion] = ele;
			return;
		}
		lastPostion++;
		
		// resize array if array is two small
		if(lastPostion > heaps.length -1) {
			Integer[] tmp = new Integer[2*heaps.length];
			System.arraycopy(heaps, 0, tmp, 0, heaps.length);
			heaps = tmp;
		}
		int parent = lastPostion / 2;
		int testPosition = lastPostion;
		while (parent > 0 && heaps[parent] > ele) {
			heaps[testPosition] = heaps[parent];
			testPosition = parent;
			parent = parent / 2;
		}
		heaps[testPosition] = ele;
	}

	public void printSorted() {
		// backup the container, and after print restore it
		Integer[] tmp = new Integer[heaps.length];
		int tmplastPostion = lastPostion;
		System.arraycopy(heaps, 0, tmp, 0, heaps.length);;
		System.out.print("[");
		while(this.lastPostion >0) {
			System.out.print(this.removeFirst()+ ", ");
		}
		lastPostion = tmplastPostion;
		heaps = tmp;
		System.out.println();
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

	private void buildHeap(int i) {
		if (i >= lastLeft) {
			return;
		}
		if (heaps[i] != null) {
			int changePostion = 0;
			int small = 0;
			if (heaps[2 * i] != null) {
				buildHeap(2 * i);
			}
			if (heaps[2 * i + 1] != null) {
				buildHeap(2 * i + 1);
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
				buildHeap(changePostion);
			}
		}
	}

	public Integer[] getHeaps() {
		return heaps;
	}

	public void setHeaps(Integer[] heaps) {
		this.heaps = heaps;
	}

	public int getLastLeft() {
		return lastLeft;
	}

	public void setLastLeft(int lastLeft) {
		this.lastLeft = lastLeft;
	}

	public int getLastPostion() {
		return lastPostion;
	}

	public void setLastPostion(int lastPostion) {
		this.lastPostion = lastPostion;
	}

}
