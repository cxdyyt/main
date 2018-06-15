package Utils;

import topology.Vertex;
import topology.Weight;

public class GenerateTopology {

	public static void  generate(Vertex[] vertexs,String[] detailedCons) {
		int verLen = vertexs.length;
		for(int i=0; i<verLen;i++) {
			Vertex vert = new Vertex();
			vert.setDetailedContent(detailedCons[i]);
			vert.setId(i);
			vertexs[i] = vert;
		}
	}
	public static void  generate(Vertex[] vertexs) {
		int verLen = vertexs.length;
		for(int i=0; i<verLen;i++) {
			Vertex vert = new Vertex();
			vert.setId(i);
			vertexs[i] = vert;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
