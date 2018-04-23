package Heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entity.NodeLeftist;

public class LeftistHeapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random(20);
		int mb = 2000;
		int i = 0;
		LeftistHeap<Integer> heaps = new LeftistHeap<>();
		System.out.print("The random input items are: [");
		while (i < mb) {
			int next = rand.nextInt(mb*3);
			heaps.insert(next);
			System.out.print(next+",");
			i++;
		}
		System.out.println(" ]");
		verifyLeftistHeap(heaps);
		heaps.printSorted();
		//testMerge();
	}
	public static void testMerge() {
		LeftistHeap<Integer> heaps1 = new LeftistHeap<Integer>();
		LeftistHeap<Integer> heaps2 = new LeftistHeap<Integer>();
		heaps1.insert(3);
		heaps1.insert(10);
		heaps1.insert(8);
		heaps1.insert(17);
		heaps1.insert(23);
		heaps1.insert(21);
		heaps1.insert(14);
		heaps1.insert(26);
		
		heaps2.insert(6);
		heaps2.insert(18);	
		heaps2.insert(12);
		heaps2.insert(18);
		heaps2.insert(7);
		heaps2.insert(33);
		heaps2.insert(24);
		heaps2.insert(37);
		LeftistHeap<Integer> result = heaps1.merge(heaps2);
		verifyLeftistHeap(result);
		result.printSorted();
		
	}
	
	public static boolean verifyLeftistHeap(LeftistHeap<Integer> heaps) {
		List<String> error = new ArrayList<>();
		verifyInvalidLeftistHeap(heaps.getTop(), error );
		for(String err : error) {
			System.out.println(err);
		}
		return error.isEmpty();
	}
	
	// verify that the Heaps is an Leftist Heap, if nothing print, it's a leftist heap
	private static int verifyInvalidLeftistHeap(NodeLeftist<Integer> node,List<String> error) {
		int result = -1;
		if(node == null) {
			return result;
		}
		NodeLeftist<Integer> left = node.getLeft();
		NodeLeftist<Integer> right = node.getRight();
		int leftze = -1;
		int rightze = -1;
		if(left != null) {
			leftze = verifyInvalidLeftistHeap(left,error);
		}
		if(right != null) {
			rightze = verifyInvalidLeftistHeap(right,error);
		}
		node.setZeroPathLength(Math.min(leftze, rightze)+1);
		result = node.getZeroPathLength();
		if(leftze < rightze) {
			error.add("verify Leftist of Heap result is false,the heap is not leftist,node with value : [" + node.getItem() + "] is not satisfy");
		}
		return result;
	}

}
