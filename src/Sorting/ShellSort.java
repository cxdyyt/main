package Sorting;

public class ShellSort<T extends Comparable<T>> extends CommonSort<T> {

	@Override
	public void sortInner(T[] arrs) {
		int len = arrs.length;
		int ham=len/2;
		while(ham > 0) {
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
			ham /= 2;
		}
	}

}
