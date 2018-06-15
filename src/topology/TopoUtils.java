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

	private void generateDouble(Vertex<T>[] vertexs,Class<T> type) throws IllegalAccessException, Exception {
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

    		addAdjacents(vertexs,left, new Integer[][] { {right,leftDis} },type);
    		addAdjacents(vertexs,right, new Integer[][] { {left,rightDis} },type);
            
        }

	}
	


	private void addAdjacents(Vertex<T>[] vertexs, Integer key,Integer[][] adjacentsIndex,Class<T> type) throws Exception, IllegalAccessException {
		List<Vertex<T>> adjacents = vertexs[key].getAdjacents();
		for (Integer[] i : adjacentsIndex) {
			Vertex<T> tmp = vertexs[i[0]];
			tmp.increaseRudu();
			tmp.setIndex(i[0]);
			T outingwei = type.newInstance();
			outingwei.setWeightValue(i[1]);
			vertexs[key].getOutingWeight().put(tmp, outingwei );
			adjacents.add(tmp);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
