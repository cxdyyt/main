package Array;

import java.util.Arrays;

public class AboutArray<T> {

	public void fanXinArray() {
		String[] arrs = (String[])new Object[10];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer i[] = {1,2};
		int j[] = new int[2];
		System.arraycopy(i, 0, j, 0, 1);
		System.out.println(Arrays.asList(j));
		String ss= "ss";
	}

}
