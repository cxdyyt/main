package topology;

public class DistancWeight<T extends DistancWeight<T>> implements Weight<T> {
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
	public Weight getNextBigWeight() {
		DistancWeight weight = new DistancWeight(++distance, description);
		return weight;
	}

	@Override
	public int getDistance() {
		return distance;
	}

	@Override
	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(T o) {
		
		if(this == o) {
			return 0;
		}
		if(this.distance > o.getDistance()) {
			return 1;
		}
		return -1;
	}
	
	

}
