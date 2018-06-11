package topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex<T extends Weight<T>> {
	private int index = 0;
	boolean startVertex;
	private int id;
	private int rudu = 0;
	private int fianlRudu;
	private String detailedContent = null;
	private List<Vertex<T>> adjacents = new ArrayList<Vertex<T>>();
	private boolean known = false;
	private T inWeight;
	private Map<Vertex<T>,T> outingWeight = new HashMap<Vertex<T>,T>(); // every out weight from this vertex to the key vertex
	Vertex<T> preVertex = null;

	public void resetVertex() {
		rudu = fianlRudu;
	}

	public void backupVertex() {
		fianlRudu = rudu;
	}

	public String getDetailedContent() {
		return detailedContent;
	}

	public Vertex<T> getPreVertex() {
		return preVertex;
	}

	public void setPreVertex(Vertex<T> preVertex) {
		this.preVertex = preVertex;
	}

	public void setDetailedContent(String detailedContent) {
		this.detailedContent = detailedContent;
	}

	public List<Vertex<T>> getAdjacents() {
		return adjacents;
	}

	public void setAdjacents(List<Vertex<T>> adjacents) {
		this.adjacents = adjacents;
	}

	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean known) {
		this.known = known;
	}

	public T getInWeight() {
		return inWeight;
	}

	public void setInWeight(T inWeight) {
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
		if (preVertex == null) {
			if (!startVertex) {
				System.out.println("this is no way from start vetext to here");
			}
		}else {
			preVertex.printPath();
		}
		System.out.print(this.index + " >> ");
	}

	public Map<Vertex<T>, T> getOutingWeight() {
		return outingWeight;
	}

	public void setComingWeight(Map<Vertex<T>, T> comingWeight) {
		outingWeight = comingWeight;
	}

	public T getNextWeightStepOne() {
		return inWeight.cloneWeightNextBig();
	}


	public T getNextWeight(Vertex<T> vert) {
		return this.inWeight.addWeight((T)outingWeight.get(vert));
	}
	
}
