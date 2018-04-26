package Sorting;

public class BucketSort extends CommonSort<Integer> {

	int maxValue;
	public BucketSort(int maxValue) {
		super();
		this.maxValue = maxValue;
	}
	@Override
	public void sortInner(Integer[] arrs) {
		int[] tmpArr = new int[maxValue];
		for(int item : arrs) {
			tmpArr[item]++;
		}
		int j=0;
		for(int i=0;i<tmpArr.length;i++) {
			int itm = tmpArr[i];
			if(itm > 0) {
				while(itm >0 ) {
					arrs[j] = i;
					itm--;
					j++;
				}
			}
		}
	}
	
}
