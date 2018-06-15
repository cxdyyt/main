package topology;

import java.util.List;

import Heaps.LeftistHeap;

public class Dijkstra<T extends Weight<T>> extends SmallPathTop<T>{

	LeftistHeap<Vertex<T>> heaps = new LeftistHeap<Vertex<T>>();
	

	public Dijkstra() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Dijkstra(Vertex<T>[] vertexs) {
		super(vertexs);
	}
	

	public Dijkstra(Vertex<T>[] vertexs, Vertex<T> topVertex, Class<T> type)
			throws InstantiationException, IllegalAccessException {
		super(vertexs, topVertex, type);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void generateSmallPathToOther(Vertex<T> topVertex) {
		//topVertex.setInWeight((T) new DistancWeight(0));
		heaps.insert(topVertex);
		while(heaps.hasMore()) {
			Vertex<T> vert = heaps.removeFirst();
			if(vert.isKnown()) {
				continue;
			}
			vert.setKnown(true);
			
			// update ajds
			List<Vertex<T>> adjs = vert.getAdjacents();
			for(Vertex<T> adj : adjs) {
				T currwei = adj.getInWeight();
				T nextWei = vert.getNextWeight(adj);
				if(currwei == null || currwei.compareTo(nextWei) > 0) {
					adj.setInWeight(vert.getNextWeight(adj));
					adj.setPreVertex(vert);
					heaps.insert(adj);
				}
			}
		}
		
	}
	
	public static void main(String ats[]) throws Exception {
		//Dijkstra<DistancWeight> dij = new Dijkstra<>(vertexs);
//		dij.setTopVertex(dij.getVertexs()[0],DistancWeight.class);
//		dij.generateSmallPathToOther();
//
//		for (Vertex<DistancWeight> ve : dij.getVertexs()) {
//			System.out.println("path from [" + dij.getTopVertex().getIndex() + "] to [" + ve.getIndex() + "]");
//			ve.printPath();
//			System.out.print("weight value is :"+(ve.getInWeight() == null ? "" : ve.getInWeight().getWeightValue()));
//			System.out.println("---------------");
//		}
	}
	
}
