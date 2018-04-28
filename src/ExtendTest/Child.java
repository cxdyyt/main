package ExtendTest;

import Fanxin.Fanxin;

public class Child extends Parent {
	String obj = "2";
	final String str ="ss";
	void method(long a) {
		System.out.println("child");
	}

//	void f() {
//		System.out.println("child f()"+ obj);
//	}
	static void aboutFinal(Fanxin fx) {
		fx = new Fanxin();
	}
	public static void main(String aef[]) {
		Parent ch = new Child();
		ch.f();
		System.out.println(ch.obj);
	}
}
