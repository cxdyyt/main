package topology;

public interface Weight<T> extends Comparable<T>{
	Integer getWeightValue();
	String getDescription();
	Weight getNextBigWeight();
	void setDistance(int distance);
	int getDistance();
}
