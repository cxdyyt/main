package topology;

public interface SmallPath<T extends Weight<T>> {

	void generateSmallPathToOther() throws Exception;

	Vertex<T> getTopVertex();

	Vertex<T>[] getVertexs();
	
	void setVertexs(Vertex<T>[] topVertex);
	
	void setTopVertex(Vertex<T> topVertex,Class<T> type) throws InstantiationException, IllegalAccessException;

}