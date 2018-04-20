package arithmetic;

public class Arithmetic {

	public int arti15(int n) {
		int mod = n%2;
		if(mod == 0) {
			return arti15(n+1)-1;
		}else {
			return arti15(n/2) + 1;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
