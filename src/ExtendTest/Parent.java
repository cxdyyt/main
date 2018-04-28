package ExtendTest;

public class Parent {
	int obj = 1;
	Parent() {
		
	}
	
	void f() {
		System.out.println("parent f()"+ obj);
	}
	void method(int a) {
		
	}
	void method(long a) {
		System.out.println("parent");
	}
	void printObj() {
		System.out.println(obj);
	}
	
}
