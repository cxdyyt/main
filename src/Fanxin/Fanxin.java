package Fanxin;

public class Fanxin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Child ch = new Child();
		ch.Sys(ch);
	}

}
class Pare1 implements Comparable<String> {

	@Override
	public int compareTo(String o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
class Pare<T extends Pare<T>> {

	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void Sys(T t) {
		System.out.println(t.getClass().getSimpleName());
	}
	
}
class Child extends Pare{

}
