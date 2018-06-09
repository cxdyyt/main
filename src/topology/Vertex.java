package topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex {
	boolean startVertex;
	private int id;
	private int index = 0;
	private int rudu = 0;
	private int fianlRudu;
	private String detailedContent = null;
	private List<Vertex> adjacents = new ArrayList<Vertex>();
	private boolean known = false;
	private Weight inWeight;
	private Map<Vertex,Weight> outingWeight = new HashMap<Vertex,Weight>(); // every income weight to this vertex
	Vertex preVertex = null;

	public void resetVertex() {
		rudu = fianlRudu;
	}

	public void backupVertex() {
		fianlRudu = rudu;
	}

	public String getDetailedContent() {
		return detailedContent;
	}

	public Vertex getPreVertex() {
		return preVertex;
	}

	public void setPreVertex(Vertex preVertex) {
		this.preVertex = preVertex;
	}

	public void setDetailedContent(String detailedContent) {
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
		if (preVertex == null) {
			if (!startVertex) {
				System.out.println("this is no way from start vetext to here");
			}
		}else {
			preVertex.printPath();
		}
		System.out.print(this.index + " >> ");
	}

	public Map<Vertex, Weight> getOutingWeight() {
		return outingWeight;
	}

	public void setComingWeight(Map<Vertex, Weight> comingWeight) {
		outingWeight = comingWeight;
	}

	public Weight getNextWeightStepOne() {
		return inWeight.cloneWeightNextBig();
	}


	public Weight getNextWeight(Vertex vert) {
		return this.inWeight.addWeight(outingWeight.get(vert));
	}
	
}
