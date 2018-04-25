package Sorting;

public class ShellSort<T extends Comparable<T>> extends CommonSort<T> {

	@Override
	public void sortInner(T[] arrs) {
		int[] hams = new int[] {5,3,1};
		int len = arrs.length;
		for(int i=0;i<hams.length;i++) {
			int ham = hams[i];
			for(int j=ham;j<len;j++) {
				T curr = arrs[j];
				for(int k=j;k>=0;k=k-ham) {
					int preInd = k-ham;
					if(preInd <0) {
						arrs[k] = curr;
						break;
					}else {
						T pre = arrs[preInd];
						if(pre.compareTo(curr) > 0) {
							arrs[k] = pre;
						}else {
							arrs[k] = curr;
							break;
						}
					}
				}
			}
		}
	}

}
