package ExtendTest;

import Fanxin.Fanxin;

public class Child extends Parent {
	Object obj = "a";
	final String str ="ss";
	void method(long a) {
		System.out.println("child");
	}

	void f(int i) {
		
	}
	static void aboutFinal(Fanxin fx) {
		fx = new Fanxin();
	}
	public static void main(String aef[]) {
		Parent ch = new Child();
		ch.printObj();
		ch.method(0L);
		final Fanxin fx = new Fanxin();
		aboutFinal(fx);
	}
}
