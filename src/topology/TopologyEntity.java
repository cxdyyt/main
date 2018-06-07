package topology;

import java.util.ArrayList;
import java.util.List;

import Utils.GenerateTopology;

public class TopologyEntity {
	private Vertex[] vertexs = new Vertex[10];
	private String[] detalCon = {"a","b","c","d","e","f","g","h","i","j"};
	private Weight weight = new DistancWeight();
	public TopologyEntity() {
		super();
		GenerateTopology.generate(vertexs, detalCon, weight);
	}
	
	public int[] topSort() {
		int[] result = new int[vertexs.length];
		
		for(Vertex vertex : vertexs) {
			
		}
		
		return result;
	}
	
	private void buildPath() {
		
		addAdjacents(vertexs[0], new int[] {1,2});
		addAdjacents(vertexs[1], new int[] {3,4});
		addAdjacents(vertexs[2], new int[] {3,7});
		addAdjacents(vertexs[3], new int[] {6});
		addAdjacents(vertexs[4], new int[] {5});
		addAdjacents(vertexs[6], new int[] {7,8});
		addAdjacents(vertexs[7], new int[] {9});
		
	}
	private void addAdjacents(Vertex vertex,int[] adjacentsIndex) {
		List<Vertex> adjacents = new ArrayList<Vertex>();
		for(int i : adjacentsIndex) {
			vertexs[i].increaseRudu();
			adjacents.add(vertexs[i]);
		}
		vertex.setAdjacents(adjacents);
	}
}
