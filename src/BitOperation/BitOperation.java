package BitOperation;

import java.util.ArrayList;
import java.util.List;

public class BitOperation {

	// get all the position value of the bit that not equal 0
	// for example num = 13, output value will be 0,2,3
	// num = 2147483647, output will be [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 
	// 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30]
	public static List<Byte> pickUpBits(int num) {
		List<Byte> result = new ArrayList<Byte>();
		Integer mitmp = 1;
		if((1 & num) == 1) {
			byte bty = 0;
			result.add(bty);
		}
		for(byte i=1;i<=30;i++) {
			mitmp <<= 1;
			if((num & mitmp) == mitmp) {
				result.add(i);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tst = 2147483647;
		List<Byte> res = BitOperation.pickUpBits(tst);
		System.out.println(res);
	}

}
