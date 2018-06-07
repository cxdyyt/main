package topology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex<T> {
	int rudu = 0;
	T detailedContent = null;
	List<Vertex> adjacents;
	
	Map<Vertex, Weight> weightMap = new HashMap<Vertex, Weight>();

	public T getDetailedContent() {
		return detailedContent;
	}

	public void setDetailedContent(T detailedContent) {
		this.detailedContent = detailedContent;
	}

	public List<Vertex> getAdjacents() {
		return adjacents;
	}

	public void setAdjacents(List<Vertex> adjacents) {
		this.adjacents = adjacents;
	}

	public Map<Vertex, Weight> getWeightMap() {
		return weightMap;
	}

	public void setWeightMap(Map<Vertex, Weight> weightMap) {
		this.weightMap = weightMap;
	}

	public int getRudu() {
		return rudu;
	}

	public void setRudu(int rudu) {
		this.rudu = rudu;
	}
	public void increaseRudu() {
		rudu++;
	}
	public void decreaseRudu() {
		rudu--;
	}
}
