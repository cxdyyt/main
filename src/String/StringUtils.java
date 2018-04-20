package String;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

class Frut{};
class Apple extends Frut{};
public class StringUtils{

	public static <T extends Serializable & Comparable<T>> T generateIns(Class<T> type) throws InstantiationException, IllegalAccessException {
		return type.newInstance();
	}
	public static <T> T[] generateArray(Class<T> type) throws InstantiationException, IllegalAccessException {
		return (T[]) Array.newInstance(type, 10);
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		ArrayList<Frut> frut = new ArrayList<Frut>();
		Apple app = new Apple();
		frut.add(app);
	}

}
