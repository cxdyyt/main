package topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.GenerateTopology;

public class SmallPathTop {
	private Vertex[] vertexs = new Vertex[10];
	private String[] detalCon = {"a","b","c","d","e","f","g","h","i","j"};
	private Weight weight = new DistancWeight();
	private Vertex topVertex;

	private Map<Vertex,VerTexWays> verTexWays = new HashMap<Vertex,VerTexWays>();
	
	public SmallPathTop() {
		super();
		GenerateTopology.generate(vertexs, detalCon, weight);
		
		addAdjacents(vertexs[0], new int[] {1,2});
		addAdjacents(vertexs[1], new int[] {3,4});
		addAdjacents(vertexs[2], new int[] {3,7});
		addAdjacents(vertexs[3], new int[] {6});
		addAdjacents(vertexs[4], new int[] {5});
		addAdjacents(vertexs[5], new int[] {8});
		addAdjacents(vertexs[6], new int[] {7,8});
		addAdjacents(vertexs[7], new int[] {0,9});
		addAdjacents(vertexs[8], new int[] {1});
		addAdjacents(vertexs[9], new int[] {6});
	}

	public void smallPathToOther() {
		
	}
	
	private void addAdjacents(Vertex vertex,int[] adjacentsIndex) {
		List<Vertex> adjacents = new ArrayList<Vertex>();
		for(int i : adjacentsIndex) {
			Vertex tmp = vertexs[i];
			tmp.increaseRudu();
			tmp.setIndex(i);
			adjacents.add(tmp);
		}
		vertex.setAdjacents(adjacents);
	}
	public Vertex getTopVertex() {
		return topVertex;
	}

	public void setTopVertex(Vertex topVertex) {
		this.topVertex = topVertex;
	}
	
	public static void main(String[] args) {
		SmallPathTop te = new SmallPathTop();
	}
}
