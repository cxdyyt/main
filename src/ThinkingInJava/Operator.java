package ThinkingInJava;

public class Operator {
	
	public static  void variable(float arg,char... ch) {
	}
	public static void variable(int arg,char... ch) {
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 2;
		int b = 256;
		byte aa ='f';
		int in = +aa;
		System.out.println();
		System.out.println(aa);
		a = a^b;
	    b = b^a;
		a = a^b;
//		variable(2L);
//		variable(2);
		variable(1);
		variable(2F);
//		System.out.println(a + "  "+ b);
//		System.out.println(5+2 + "s");
	}

}
