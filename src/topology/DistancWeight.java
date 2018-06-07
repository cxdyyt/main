package topology;

public class DistancWeight implements Weight {
	int distance;
	String description = "Distane between two point";

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

}
