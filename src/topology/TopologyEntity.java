package topology;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Utils.GenerateTopology;

public class TopologyEntity {
	private Vertex[] vertexs = new Vertex[10];
	private String[] detalCon = {"a","b","c","d","e","f","g","h","i","j"};
	private Weight weight = new DistancWeight();
	public TopologyEntity() {
		super();
		GenerateTopology.generate(vertexs, detalCon);
		
		addAdjacents(vertexs[0], new int[] {1,2});
		addAdjacents(vertexs[1], new int[] {3,4});
		addAdjacents(vertexs[2], new int[] {3,7});
		addAdjacents(vertexs[3], new int[] {6});
		addAdjacents(vertexs[4], new int[] {5});
		addAdjacents(vertexs[6], new int[] {7,8});
		addAdjacents(vertexs[7], new int[] {9});
		

		for(Vertex vert : vertexs) {
			vert.backupVertex();
		}
	}
	private void resetTopology() {
		for(Vertex vert : vertexs) {
			vert.resetVertex();
		}
	}

	public List<Vertex> topSort() {
		List<Vertex> result = new ArrayList<Vertex>();
		LinkedList<Vertex> zeroRuduList = new LinkedList<Vertex>();
		for(Vertex vertex : vertexs) {
			if(vertex.getRudu() == 0) {
				zeroRuduList.push(vertex);
			}
		}
		
		int j=0;
		while(!zeroRuduList.isEmpty()) {
			j++;
			Vertex zeroVer = zeroRuduList.pop();
			result.add(zeroVer);
			List<Vertex> adjacents = zeroVer.getAdjacents();
			for(Vertex vertex : adjacents) {
				vertex.decreaseRudu();
				if(vertex.getRudu() == 0) {
					zeroRuduList.push(vertex);
				}
			}
		}
		resetTopology();
		return result;
	}
	public String topSortCentent() {
		StringBuffer res = new StringBuffer();;
		List<Integer> restint = new ArrayList<Integer>();
		List<Vertex> rest = topSort();
		for(Vertex ver : rest) {
			res.append(ver.getDetailedContent() + ",");
		}
		return res.toString();
	}
	public String topSortIndex() {
		StringBuffer res = new StringBuffer();;
		List<Integer> restint = new ArrayList<Integer>();
		List<Vertex> rest = topSort();
		for(Vertex ver : rest) {
			res.append(ver.getIndex() + ",");
		}
		return res.toString();
	}
	
	
	private void resetToplogy() {
		
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
			Vertex tmp = vertexs[i];
			tmp.increaseRudu();
			tmp.setIndex(i);
			adjacents.add(tmp);
		}
		vertex.setAdjacents(adjacents);
	}
	
	public static void main(String[] args) {
		TopologyEntity te = new TopologyEntity();
		System.out.println(te.topSortIndex());
		System.out.println(te.topSortCentent());
	}
}
