package topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Utils.GenerateTopology;

public class SmallPathTop {
	private Vertex[] vertexs = new Vertex[10];
	private String[] detalCon = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
	private Weight weight = new DistancWeight(0);
	private Vertex topVertex;

	public SmallPathTop() {
		super();
		GenerateTopology.generate(vertexs, detalCon, weight);

		addAdjacents(vertexs[0], new int[] { 1, 2 });
		addAdjacents(vertexs[1], new int[] { 3, 4 });
		addAdjacents(vertexs[2], new int[] { 3, 7 });
		addAdjacents(vertexs[3], new int[] { 6 });
		addAdjacents(vertexs[4], new int[] { 5 });
		addAdjacents(vertexs[5], new int[] { 8 });
		addAdjacents(vertexs[6], new int[] { 7, 8 });
		addAdjacents(vertexs[7], new int[] { 0, 9 });
		addAdjacents(vertexs[8], new int[] {1});
		addAdjacents(vertexs[9], new int[] { 6 });
	}

	public void generateSmallPathToOther() throws Exception {
		if (topVertex == null) {
			throw new Exception("Top vertex is null");
		}
		generateSmallPathToOther(topVertex);
	}

	public void generateSmallPathToOther(Vertex topVertex) {
		if (topVertex != null) {

			// Weight currentWei = new DistancWeight(0);

			LinkedList<Vertex> exitVer = new LinkedList<Vertex>();
			topVertex.setRudu(0);
			topVertex.setKnown(true);
			// trySetVetexKnown(topVertex);
			pushAdjacents(exitVer, topVertex);

			while (!exitVer.isEmpty()) {
				Vertex vert = exitVer.poll();
				if (vert.getRudu() == 0) {
					vert.setKnown(true);
				}
				pushAdjacents(exitVer, vert);
				// }
			}
		}
	}


	private void pushAdjacents(LinkedList<Vertex> pushAdjacents, Vertex vertex) {
		List<Vertex> vetexs = vertex.getAdjacents();
		for (Vertex vert : vetexs) {
			if (vert.isKnown()) {
				continue;
			}

			Weight nextWei = vertex.getNextWeight(vert);
			Vertex currentPreVer = vert.getPreVertex();
			if (currentPreVer == null || vert.getInWeight().compareTo(nextWei) > 0) {
				vert.decreaseRudu();
				vert.setInWeight(nextWei);
				vert.setPreVertex(vertex);
				pushAdjacents.add(vert);
			}
		}
	}

	private void addAdjacents(Vertex vertex, int[] adjacentsIndex) {
		List<Vertex> adjacents = new ArrayList<Vertex>();
		for (int i : adjacentsIndex) {
			Vertex tmp = vertexs[i];
			tmp.increaseRudu();
			tmp.setIndex(i);
			Weight inWeight = new DistancWeight<>(0);
			tmp.setInWeight(inWeight);
			adjacents.add(tmp);
		}
		vertex.setAdjacents(adjacents);
	}

	public Vertex getTopVertex() {
		return topVertex;
	}

	public void setTopVertex(Vertex topVertex) {
		this.topVertex = topVertex;
		topVertex.setStartVertex(true);
	}

	public Vertex[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(Vertex[] vertexs) {
		this.vertexs = vertexs;
	}

	public String[] getDetalCon() {
		return detalCon;
	}

	public void setDetalCon(String[] detalCon) {
		this.detalCon = detalCon;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}

	public static void main(String[] args) throws Exception {
		SmallPathTop te = new SmallPathTop();
		te.setTopVertex(te.getVertexs()[0]);
		te.generateSmallPathToOther();
		for (Vertex ve : te.getVertexs()) {
			System.out.println("path from [" + te.getTopVertex().getIndex() + "] to [" + ve.getIndex() + "]");
			ve.printPath();
			System.out.print("weight value is :"+ve.getInWeight().getWeightValue());
			System.out.println("---------------");
		}
		System.out.println("");
	}
}
