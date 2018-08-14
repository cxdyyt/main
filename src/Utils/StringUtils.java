package Utils;

import java.util.Arrays;

public class StringUtils {
	public static void print(Object obj){
		System.out.print(obj);
	}
	public static void print(Object obj[]){
		System.out.print(Arrays.toString(obj));
	}
	public static void println(Object obj){
		System.out.println(obj);
	}
	
	public static void println(Object obj[]){
		System.out.println(Arrays.toString(obj));
	}
	public static boolean isEmpty(String str){
		return (str == null || str.trim().equals(""));
	}
}
