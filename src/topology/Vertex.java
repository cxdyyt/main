package topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex<T> {
	private int index = 0;
	private int rudu = 0;
	private int fianlRudu;
	private T detailedContent = null;
	private List<Vertex> adjacents = new ArrayList<Vertex>();
	
	public void resetVertex() {
		rudu = fianlRudu;
	}

	public void backupVertex() {
		fianlRudu = rudu;
	}
	private Map<Vertex, Weight> weightMap = new HashMap<Vertex, Weight>();

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

	public void setFianlRudu(int rudu) {
		this.fianlRudu = rudu;
	}
	public void increaseRudu() {
		rudu++;
	}
	public void decreaseRudu() {
		rudu--;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getFianlRudu() {
		return fianlRudu;
	}

}
