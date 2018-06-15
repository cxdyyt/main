package topology;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import Utils.GenerateTopology;

public class TopoUtils<T extends Weight<T>> {

	public static <T extends Weight<T>> void generatorVertexs(Vertex<T>[] vertexs,Map<Integer,Integer[][]> pathMap,Class<T> type) throws Exception, Exception {

		GenerateTopology.generate(vertexs);
		Set<Integer> keys = pathMap.keySet();
		for(int key : keys) {
			List<Vertex<T>> adjacents = vertexs[key].getAdjacents();
			Integer[][]  adjacentsIndex = pathMap.get(key);
			for (Integer[] i : adjacentsIndex ) {
				Vertex<T> tmp = vertexs[i[0]];
				tmp.increaseRudu();
				tmp.setIndex(i[0]);
				T outingwei = type.newInstance();
				outingwei.setWeightValue(i[1]);
				vertexs[key].getOutingWeight().put(tmp, outingwei );
				adjacents.add(tmp);
			}
			
		}
		
	}
	
	
	public static <T extends Weight<T>> boolean isTwoStrategyEqual(Vertex<T>[] vertexs1,Vertex<T>[] vertexs2) {
		for(int i=0;i<vertexs1.length;i++) {
			Vertex<T> ver1 = vertexs1[i];
			Vertex<T> ver2 = vertexs2[i];
			T weight1 = ver1.getInWeight();
			T weight2 = ver2.getInWeight();
			if(!isWeightEqual(weight1, weight2)) {
				System.out.println("vertex1: " + ver1 + "vertex2: " + ver2);
				return false;
			}
		}
		return true;
	}
	
	public static <T extends Weight<T>> boolean isWeightEqual(T wei1,T wei2) {
		if(wei1 == null) {
			if(wei2 != null) {
				return false;
			}else {
				return true;
			}
		}else {
			if(wei2 == null) {
				return false;
			}else {
				if(wei1.compareTo(wei2) != 0) {
					return false;
				}else {
					return true;
				}
			}
		}
	}

	public static <T extends Weight<T>>  void generateRandom(Vertex<T>[] vertexs,Class<T> type) throws InstantiationException, IllegalAccessException {
		GenerateTopology.generate(vertexs);
		Random disRan = new Random(55);
        int oldCapacity = vertexs.length-1;
//        Random ran = new Random();
        int jjj = oldCapacity*2;
        Random ran = new Random(37);
        while(jjj-- >= 0) {
            int left = ran.nextInt(oldCapacity);
            int right = ran.nextInt(oldCapacity);
            if(left == right) {
            	continue;
            }
            int leftDis = disRan.nextInt(20)+1;
            int rightDis = disRan.nextInt(20)+1;

    		addAdjacents(vertexs,left, new int[][] { {right,leftDis} },type);
    		addAdjacents(vertexs,right, new int[][] { {left,rightDis} },type);
            
        }

	}	



	private static <T extends Weight<T>> void addAdjacents(Vertex<T>[] vertexs,int fromin, int[][] adjacentsIndex,Class<T> type) throws InstantiationException, IllegalAccessException {
		List<Vertex<T>> adjacents = vertexs[fromin].getAdjacents();
		for (int[] i : adjacentsIndex) {
			Vertex<T> tmp = (Vertex<T>) vertexs[i[0]];
			tmp.increaseRudu();
			tmp.setIndex(i[0]);
			T outingwei = type.newInstance();
			outingwei.setWeightValue(i[1]);
			vertexs[fromin].getOutingWeight().put(tmp, outingwei );
			adjacents.add(tmp);
		}
	}

}
