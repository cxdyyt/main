package topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex<T> {
	boolean startVertex;
	private int id;
	private int index = 0;
	private int rudu = 0;
	private int fianlRudu;
	private T detailedContent = null;
	private List<Vertex> adjacents = new ArrayList<Vertex>();
	private boolean known = false;
	private Weight inWeight;
	Vertex preVertex = null;

	public void resetVertex() {
		rudu = fianlRudu;
	}

	public void backupVertex() {
		fianlRudu = rudu;
	}

	public T getDetailedContent() {
		return detailedContent;
	}

	public Vertex getPreVertex() {
		return preVertex;
	}

	public void setPreVertex(Vertex preVertex) {
		this.preVertex = preVertex;
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

	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean known) {
		this.known = known;
	}

	public Weight getInWeight() {
		return inWeight;
	}

	public void setInWeight(Weight inWeight) {
		this.inWeight = inWeight;
	}

	public void setRudu(int rudu) {
		this.rudu = rudu;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStartVertex() {
		return startVertex;
	}

	public void setStartVertex(boolean startVertex) {
		this.startVertex = startVertex;
	}

	public void printPath() {
		if (preVertex != null) {
			preVertex.printPath();
		} else if (!startVertex) {
			System.out.println("this is no way from start vetext to here");
		} else {
			System.out.print(this.index + " >> ");
		}
	}

}
