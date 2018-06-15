package topology;

public class DistancWeight implements Weight<DistancWeight> {
	int distance = 0;
	String description = "Distane between two point";

	public DistancWeight() {
		super();
	}

	public DistancWeight(int distance) {
		super();
		this.distance = distance;
	}

	public DistancWeight(int distance, String description) {
		super();
		this.distance = distance;
		this.description = description;
	}

	@Override
	public Integer getWeightValue() {
		// TODO Auto-generated method stub
		return distance;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + distance;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DistancWeight other = (DistancWeight) obj;
		if (distance != other.distance)
			return false;
		return true;
	}

	@Override
	public DistancWeight cloneWeight() {
		DistancWeight weight = new DistancWeight(distance, description);
		return weight;
	}

	@Override
	public void setWeightValue(int weightValue) {
		this.distance = weightValue;
	}

	@Override
	public DistancWeight cloneWeightNextBig() {
		return new DistancWeight(distance + 1);
	}

	@Override
	public DistancWeight addWeight(DistancWeight wei) {
		int weightValAdd = this.getWeightValue() + wei.getWeightValue();
		return new DistancWeight(weightValAdd);
	}


	@Override
	public int compareTo(DistancWeight o) {
		if (this == o) {
			return 0;
		}
		if (this.getWeightValue() > o.getWeightValue()) {
			return 1;
		}else if(this.getWeightValue() == o.getWeightValue())  {
			return 0;
		}else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return "DistancWeight [distance=" + distance + "]";
	}

}
