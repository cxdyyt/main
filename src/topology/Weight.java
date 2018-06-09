package topology;

public interface Weight<T extends Weight<T>> extends Comparable<T>{
	Integer getWeightValue();
	void setWeightValue(int weightValue);
	String getDescription();
	T cloneWeight();
	T cloneWeightNextBig();
	T addWeight(T wei);
}
