package String;

import java.util.ArrayList;
import java.util.List;

public class GenrWrite{
	static <T> void writeExact(List<? super T> list ,T item) {
		list.add(item);
		List<?> lsit = new ArrayList<String>();
	}
	static List<Frut> frut12 = new ArrayList<Frut>();
	static void f() {
		GenrWrite.<Apple>writeExact(frut12, new Apple());
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		f();
		List<?> list = new ArrayList();
	}
}
