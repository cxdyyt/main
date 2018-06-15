package topology;

import java.util.LinkedList;
import java.util.List;

public class SmallPathTop<T extends Weight<T>> extends AbsctractSmallPath<T> implements SmallPath<T> {
	
	private Vertex<T>[] vertexs;
	Vertex<T> topVertex;

	
	
	public SmallPathTop() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SmallPathTop(Vertex<T>[] vertexs) {
		super();
		this.vertexs = vertexs;
	}


	public SmallPathTop(Vertex<T>[] vertexs,Vertex<T> topVertex,Class<T> type) throws InstantiationException, IllegalAccessException {
		this(vertexs);
		this.setTopVertex(topVertex,type);
	}


	
	public void generateSmallPathToOther(Vertex<T> topVertex) {
		if (topVertex != null) {

			LinkedList<Vertex<T>> vertexQueue = new LinkedList<Vertex<T>>();
			vertexQueue.add(topVertex);
			
			while (!vertexQueue.isEmpty()) {
				
				Vertex<T> vertPoll = vertexQueue.poll();
				vertPoll.setInQueue(false);

				List<Vertex<T>> vetexs = vertPoll.getAdjacents();
				for (Vertex<T> vert : vetexs) {

					T nextWei = vertPoll.getNextWeight(vert);
					if (vert.getInWeight() == null || vert.getInWeight().compareTo(nextWei) > 0) {
						vert.setInWeight(nextWei);
						vert.setPreVertex(vertPoll);
						if(!vert.isInQueue()) {
							vertexQueue.add(vert);
							vert.setInQueue(true);
						}
					}
				}
			
				
			}
		}
	}



	/* (non-Javadoc)
	 * @see topology.SmallPath#getTopVertex()
	 */
	@Override
	public Vertex<T> getTopVertex() {
		return topVertex;
	}
	@Override
	public void setTop(Vertex<T> topVertex) throws InstantiationException, IllegalAccessException {
		this.topVertex = topVertex;
	}

	/* (non-Javadoc)
	 * @see topology.SmallPath#getVertexs()
	 */
	@Override
	public Vertex<T>[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(Vertex<T>[] vertexs) {
		this.vertexs = vertexs;
	}

	public void printAllWay() {
		for(Vertex<T> vertex : vertexs) {
			List<Vertex<T>> vertexAjds = vertex.getAdjacents();
			for(Vertex<T> vt : vertexAjds) {
				System.out.println(vertex.getIndex() + ">>" + vt.getIndex()+ "|" + vertex.getOutingWeight().get(vt).getWeightValue());
			}
		}
	}
}
