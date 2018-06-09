package topology;

public interface Weight<T> extends Comparable<T>{
	Integer getWeightValue();
	void setWeightValue(int weightValue);
	String getDescription();
	Weight cloneWeight();
	Weight cloneWeightNextBig();
}
