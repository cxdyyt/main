package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import Heaps.BinaryHeap;
import Heaps.LeftistHeap;
import InterFaces.HeapInterface;
import InterFaces.SortFunction;
import Sorting.BubbleSort;
import Sorting.BucketSort;
import Sorting.HeapSort;
import Sorting.InsertionSort;
import Sorting.MergeSort;
import Sorting.QuickSort;
import Sorting.QuickSortWithMin;
import Sorting.SelectionSort;
import Sorting.ShellSort;

public class SortingTest {
	private static void testServeralSorting(List<SortFunction<Integer>> sorts,boolean isPrint,int len,int seek) {
		for(SortFunction sort : sorts) {
			sort.sort((gernerateItems(len,seek)));
			if(isPrint) {
				sort.printList();
			}
		}
	}
	
	private static Integer[] gernerateItems(int len,int seek) {
		Integer[] arrs = new Integer[len];
		Random ran = new Random(seek);
		//System.out.println("original items");
		for(int i=0;i<len;i++) {
			int next= ran.nextInt(len*3);
			arrs[i] = next;
			//System.out.print(next + " ,");
		}
		return arrs;
	}

	private static Map<Class,Integer> timsTest(int times,int numsLen,boolean isPrint) {
		Map<Class,Integer> fastTimesResult = new HashMap<Class,Integer>();
		while(times-- > 0) {
			//System.out.println(times + "----------------------------------------------------------------------");
			List<SortFunction<Integer>> sorts = new ArrayList<SortFunction<Integer>>();
			//sorts.add(new BubbleSort<Integer>());
			//sorts.add(new SelectionSort<Integer>());
			//sorts.add(new InsertionSort<Integer>());
			//sorts.add(new ShellSort<Integer>());
			byte byt = 0;
			HeapInterface<Integer> heap = new LeftistHeap<Integer>();
			HeapInterface<Integer> heap1 = new BinaryHeap();
			//sorts.add(new HeapSort<Integer>(heap ));
			//sorts.add(new MergeSort<Integer>());
			//sorts.add(new HeapSort<Integer>(heap1 ));
			//sorts.add(new BucketSort(200*3));

			sorts.add(new QuickSort<Integer>());
			sorts.add(new QuickSortWithMin<Integer>());
			
			int seek = (int) new Date().getTime();
			testServeralSorting(sorts , isPrint,numsLen,seek );
			sortUsingPerTime(sorts);
			Class[] clas = new Class[] {BubbleSort.class,BucketSort.class,HeapSort.class,InsertionSort.class,
					MergeSort.class,QuickSort.class,QuickSortWithMin.class,SelectionSort.class,ShellSort.class};
			// count of fast
			for(int i= 0;i<clas.length;i++) {
				Class cls = clas[i];
				if(getFastClass(sorts) == cls) {
					Integer quickInt = fastTimesResult.get(cls);
					if(quickInt == null) {
						quickInt = 1;
					}else {
						quickInt++;
					}
					fastTimesResult.put(cls, quickInt);
				}
			}
		}
		
		return fastTimesResult;
	
	}
	private static Class<?>  getFastClass(List<SortFunction<Integer>> sorts) {
		return sorts.get(0).getClass();
	}
	/**
	 *  sort sorts using perTime value with aesc order
	 * @param sorts
	 */
	private static void sortUsingPerTime(List<SortFunction<Integer>> sorts) {
		Collections.sort(sorts);
		System.out.println(sorts);
	}
	
	public static void main(String[] args) {
		Map<Class,Integer> fastTimesResult = timsTest(2,100,true);
		Set<Class>  keys = fastTimesResult.keySet();
		for(Class cls : keys) {
			System.out.println(cls.getSimpleName() + " fasted count times is[" + fastTimesResult.get(cls) + "]");
		}
	}
}
