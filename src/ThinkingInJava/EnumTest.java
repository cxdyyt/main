package ThinkingInJava;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import InterFaces.SortFunction;
import Utils.StringUtils;

public class EnumTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		Color red = Color.RED;
		Color bl = Color.BLACK;
		StringUtils.println(red == bl);
		StringUtils.println(red.getDeclaringClass());
		StringUtils.println(red.name());
		Color red1 = Enum.valueOf(Color.class, "RED");
		StringUtils.println(red1);
		red.valueOf("BLACK");
		red.printList();
		System.out.println("enumCons " + Arrays.toString(Color.class.getEnumConstants()));
		EnumSet es = EnumSet.of(red) ;
		es.add(Color.BLUE);
		System.out.println(es.contains("RED"));
		EnumMap<Color, String> emp = new EnumMap<Color, String>(Color.class);
		//emp.put(red, "2");
		
		Map<Color,String> map = new HashMap<Color,String>();
		map.put(red, "a");
		EnumMap<Color, String> emp1 = new EnumMap<Color, String>(map);
		System.out.println("emap: " + emp1.get(red));
		int[] aa = {1,2};
		int[] bb = aa.clone();
		System.out.println(Arrays.toString(bb));
		red.print();
	}

}

class bclass{
	
}

enum CombinColorAndFold {
	COLOR(Color.class),
	FOOD1(Food.appetizer.class);
	Class<?> enumClass;
	
	private CombinColorAndFold(Class<?> enumClass) {
		this.enumClass = enumClass;
	}
	
}
interface Food {
	enum appetizer implements Food {
		HH
	}
}
enum Color implements SortFunction<Integer>{
	RED("red") {
		@Override
		void print() {
			System.out.println("red print method");
		}
	},BLUE("Blue") {
		@Override
		void print() {
			System.out.println("blue print method");
			
		}
	},BLACK("") {
		@Override
		void print() {
			// TODO Auto-generated method stub
			
		}
	},WHITE("") {
		@Override
		void print() {
			// TODO Auto-generated method stub
			
		}
	},YELLOW("") {
		@Override
		void print() {
			// TODO Auto-generated method stub
			
		}
	};
	private String des;
	Color(String des) {
		this.des = des;
	}
	abstract void print();
	@Override
	public void sort(Integer[] arrs) {
		// TODO Auto-generated method stub
		return;
	}
	@Override
	public void printList() {
		System.out.println(Arrays.toString(values()));
	}
	
	
}
