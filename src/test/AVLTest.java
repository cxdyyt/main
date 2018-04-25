package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Trees.AVL;

public class AVLTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rang = new Random(47);
		List<Integer> list = new ArrayList<Integer>();
		AVL<Integer> avl = new AVL<Integer>();
		int ham = 100;
		int i =0;
		while(i<ham) {
			i++;
			int tmp = rang.nextInt(ham*3);
			list.add(tmp);
			avl.push(tmp);
		}
		Collections.sort(list);
		System.out.println("original items");
		System.out.println("------------------------------");
		System.out.println(list);
		System.out.println("tree items");
		avl.printlist();
	}

}
