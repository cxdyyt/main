package topology;

public class VerTexWays {
	int parentIndex = -1;
	Weight totalWeight;
	
	public VerTexWays(int parentIndex, Weight totalWeight) {
		super();
		this.parentIndex = parentIndex;
		this.totalWeight = totalWeight;
	}
	public Weight getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(Weight totalWeight) {
		this.totalWeight = totalWeight;
	}
	public int getParentIndex() {
		return parentIndex;
	}
	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}
	
}
