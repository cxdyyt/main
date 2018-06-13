package topology;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Utils.GenerateTopology;

public class SmallPathTop {
	private Vertex<DistancWeight>[] vertexs = new Vertex[10];
	private String[] detalCon = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
	private Weight<DistancWeight> weight = new DistancWeight(0);
	private Vertex<DistancWeight> topVertex;

	public SmallPathTop() {
		super();
		GenerateTopology.generate(vertexs, weight);

		addAdjacents(vertexs[0], new int[][] { {1,-100}, {2,5} });
		addAdjacents(vertexs[1], new int[][] { {0,7},{3,7}, {4,6},{8,1} });
		addAdjacents(vertexs[2], new int[][] { {3,12}, {7,2} ,{0,1}});
		addAdjacents(vertexs[3], new int[][] { {2,12},{6,15} });
		addAdjacents(vertexs[4], new int[][] { {5,10},{1,11} });
		addAdjacents(vertexs[5], new int[][] { {8,13} });
		addAdjacents(vertexs[6], new int[][] { {7,9}, {8,8} });
		addAdjacents(vertexs[7], new int[][] { {0,8}, {9,4} });
		addAdjacents(vertexs[8], new int[][] {{5,2}});
		addAdjacents(vertexs[9], new int[][] { {6,27} });
//		generateDouble();
	}


	private void generateDouble() {
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

    		addAdjacents(vertexs[left], new int[][] { {right,leftDis} });
    		addAdjacents(vertexs[right], new int[][] { {left,rightDis} });
            
        }

		addAdjacents(vertexs[0], new int[][] { {1,2} });
	}
	
	public void generateSmallPathToOther() throws Exception {
		if (topVertex == null) {
			throw new Exception("Top vertex is null");
		}
		generateSmallPathToOther(topVertex);
	}

	public void generateSmallPathToOther(Vertex<DistancWeight> topVertex) {
		if (topVertex != null) {

			LinkedList<Vertex<DistancWeight>> exitVer = new LinkedList<Vertex<DistancWeight>>();
			caculateSmallestPathWeightOfAdjs(exitVer, topVertex);

			while (!exitVer.isEmpty()) {
				Vertex<DistancWeight> vert = exitVer.poll();
				caculateSmallestPathWeightOfAdjs(exitVer, vert);
			}
		}
	}


	private void caculateSmallestPathWeightOfAdjs(LinkedList<Vertex<DistancWeight>> pushAdjacents, Vertex<DistancWeight> vertex) {
		List<Vertex<DistancWeight>> vetexs = vertex.getAdjacents();
		for (Vertex<DistancWeight> vert : vetexs) {
			if (vert == topVertex) {
				continue;
			}

			DistancWeight nextWei = vertex.getNextWeight(vert);
			Vertex<DistancWeight> currentPreVer = vert.getPreVertex();
			if (currentPreVer == null || vert.getInWeight().compareTo(nextWei) > 0) {
				vert.setInWeight(nextWei);
				vert.setPreVertex(vertex);
				if(!vert.isInQueue()) {
					pushAdjacents.add(vert);
					vert.setInQueue(true);
				}else{
					// smallest path change for ver, then re generate all path from ver to all other point
					caculateSmallestPathWeightOfAdjs(pushAdjacents, vert);
				}
			}
		}
	}

	private void addAdjacents(Vertex<DistancWeight> vertex, int[][] adjacentsIndex) {
		List<Vertex<DistancWeight>> adjacents = vertex.getAdjacents();
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

	public void printAllWay() {
		for(Vertex<DistancWeight> vertex : vertexs) {
			List<Vertex<DistancWeight>> vertexAjds = vertex.getAdjacents();
			for(Vertex<DistancWeight> vt : vertexAjds) {
				System.out.println(vertex.getIndex() + ">>" + vt.getIndex()+ "|" + vertex.getOutingWeight().get(vt).getWeightValue());
			}
		}
	}
	public static void main(String[] args) throws Exception {
		SmallPathTop te = new SmallPathTop();
		//te.printAllWay();
		te.setTopVertex(te.getVertexs()[0]);
		long startTim = new Date().getTime();
		te.generateSmallPathToOther();
		long endTim = new Date().getTime();
		for (Vertex<DistancWeight> ve : te.getVertexs()) {
			System.out.println("path from [" + te.getTopVertex().getIndex() + "] to [" + ve.getIndex() + "]");
			ve.printPath();
			System.out.print("weight value is :"+(ve.getInWeight() == null ? "" : ve.getInWeight().getWeightValue()));
			System.out.println("---------------");
		}
		System.out.println("performance is :" + (endTim - startTim));
	}
}
