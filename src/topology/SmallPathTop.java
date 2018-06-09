package topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Utils.GenerateTopology;

public class SmallPathTop {
	private Vertex<DistancWeight>[] vertexs = new Vertex[10];
	private String[] detalCon = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
	private Weight<DistancWeight> weight = new DistancWeight(0);
	private Vertex<DistancWeight> topVertex;

	public SmallPathTop() {
		super();
		GenerateTopology.generate(vertexs, detalCon, weight);

		addAdjacents(vertexs[0], new int[][] { {1,3}, {2,5} });
		addAdjacents(vertexs[1], new int[][] { {3,7}, {4,6} });
		addAdjacents(vertexs[2], new int[][] { {3,12}, {7,2} });
		addAdjacents(vertexs[3], new int[][] { {6,15} });
		addAdjacents(vertexs[4], new int[][] { {5,10} });
		addAdjacents(vertexs[5], new int[][] { {8,13} });
		addAdjacents(vertexs[6], new int[][] { {7,9}, {8,8} });
		addAdjacents(vertexs[7], new int[][] { {0,8}, {9,4} });
		addAdjacents(vertexs[8], new int[][] {{1,20}});
		addAdjacents(vertexs[9], new int[][] { {6,27} });
	}

	public void generateSmallPathToOther() throws Exception {
		if (topVertex == null) {
			throw new Exception("Top vertex is null");
		}
		generateSmallPathToOther(topVertex);
	}

	public void generateSmallPathToOther(Vertex<DistancWeight> topVertex) {
		if (topVertex != null) {

			// Weight currentWei = new DistancWeight(0);

			LinkedList<Vertex<DistancWeight>> exitVer = new LinkedList<Vertex<DistancWeight>>();
			topVertex.setRudu(0);
			topVertex.setKnown(true);
			// trySetVetexKnown(topVertex);
			pushAdjacents(exitVer, topVertex);

			while (!exitVer.isEmpty()) {
				Vertex<DistancWeight> vert = exitVer.poll();
				if (vert.getRudu() == 0) {
					vert.setKnown(true);
				}
				pushAdjacents(exitVer, vert);
				// }
			}
		}
	}


	private void pushAdjacents(LinkedList<Vertex<DistancWeight>> pushAdjacents, Vertex<DistancWeight> vertex) {
		List<Vertex<DistancWeight>> vetexs = vertex.getAdjacents();
		for (Vertex<DistancWeight> vert : vetexs) {
			if (vert.isKnown()) {
				continue;
			}

			DistancWeight nextWei = vertex.getNextWeight(vert);
			Vertex<DistancWeight> currentPreVer = vert.getPreVertex();
			if (currentPreVer == null || vert.getInWeight().compareTo(nextWei) > 0) {
				vert.decreaseRudu();
				vert.setInWeight(nextWei);
				vert.setPreVertex(vertex);
				pushAdjacents.add(vert);
			}
		}
	}

	private void addAdjacents(Vertex<DistancWeight> vertex, int[][] adjacentsIndex) {
		List<Vertex<DistancWeight>> adjacents = new ArrayList<Vertex<DistancWeight>>();
		for (int[] i : adjacentsIndex) {
			Vertex<DistancWeight> tmp = vertexs[i[0]];
			tmp.increaseRudu();
			tmp.setIndex(i[0]);
			DistancWeight inWeight = new DistancWeight(0);
			tmp.setInWeight(inWeight);
			DistancWeight outingwei = new DistancWeight(i[1]);
			vertex.getOutingWeight().put(tmp, outingwei );
			adjacents.add(tmp);
		}
		vertex.setAdjacents(adjacents);
	}

	public Vertex<DistancWeight> getTopVertex() {
		return topVertex;
	}

	public void setTopVertex(Vertex<DistancWeight> topVertex) {
		this.topVertex = topVertex;
		topVertex.setStartVertex(true);
	}

	public Vertex<DistancWeight>[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(Vertex<DistancWeight>[] vertexs) {
		this.vertexs = vertexs;
	}

	public String[] getDetalCon() {
		return detalCon;
	}

	public void setDetalCon(String[] detalCon) {
		this.detalCon = detalCon;
	}

	public Weight<DistancWeight> getWeight() {
		return weight;
	}

	public void setWeight(Weight<DistancWeight> weight) {
		this.weight = weight;
	}

	public static void main(String[] args) throws Exception {
		SmallPathTop te = new SmallPathTop();
		te.setTopVertex(te.getVertexs()[0]);
		te.generateSmallPathToOther();
		for (Vertex<DistancWeight> ve : te.getVertexs()) {
			System.out.println("path from [" + te.getTopVertex().getIndex() + "] to [" + ve.getIndex() + "]");
			ve.printPath();
			System.out.print("weight value is :"+ve.getInWeight().getWeightValue());
			System.out.println("---------------");
		}
		System.out.println("");
	}
}
