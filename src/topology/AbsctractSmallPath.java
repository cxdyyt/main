package topology;

public abstract class AbsctractSmallPath<T extends Weight<T>> implements SmallPath<T>{

	public AbsctractSmallPath() {
		super();
	}

	@Override
	public void generateSmallPathToOther() throws Exception {
		if (getTopVertex() == null) {
			throw new Exception("Top vertex is null");
		}
		generateSmallPathToOther(getTopVertex());
	}

	protected abstract void generateSmallPathToOther(Vertex<T> topVertex);

	@Override
	public abstract Vertex<T> getTopVertex();

	@Override
	public abstract Vertex<T>[] getVertexs();
	
	@Override
	public void setTopVertex(Vertex<T> topVertex,Class<T> type) throws InstantiationException, IllegalAccessException{
		setTop(topVertex);
		topVertex.setStartVertex(true);
		T inWeight = type.newInstance();
		inWeight.setWeightValue(0);
		topVertex.setInWeight(inWeight );
	}
	
	public abstract void setTop(Vertex<T> topVertex) throws InstantiationException, IllegalAccessException;

}