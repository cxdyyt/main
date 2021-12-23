package test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import topology.Dijkstra;
import topology.DistancWeight;
import topology.SmallPath;
import topology.SmallPathTop;
import topology.TopoUtils;
import topology.Vertex;

public class SmallPathTest {

    public static Vertex<DistancWeight>[] geneateStrategy(Class<? extends SmallPath> smallPathType) throws Exception {
        Vertex<DistancWeight>[] vertexsfff = new Vertex[100];
        Map<Integer, Integer[][]> pathMap = new HashMap<Integer, Integer[][]>();

        pathMap.put(0, new Integer[][] { { 1, 3 }, { 2, 5 } });
        pathMap.put(1, new Integer[][] { { 0, 7 }, { 3, 7 }, { 4, 6 }, { 8, 1 } });
        pathMap.put(2, new Integer[][] { { 3, 12 }, { 7, 2 }, { 0, 1 } });
        pathMap.put(3, new Integer[][] { { 2, 12 }, { 6, 15 } });
        pathMap.put(4, new Integer[][] { { 5, 10 }, { 1, 11 } });
        pathMap.put(5, new Integer[][] { { 8, 13 } });
        pathMap.put(6, new Integer[][] { { 7, 9 }, { 8, 8 } });
        pathMap.put(7, new Integer[][] { { 0, 8 }, { 9, 4 } });
        pathMap.put(8, new Integer[][] { { 5, 2 } });
        pathMap.put(9, new Integer[][] { { 6, 27 } });

        // TopoUtils.generatorVertexs(vertexsfff, pathMap , DistancWeight.class);
        TopoUtils.generateRandom(vertexsfff, DistancWeight.class);
        SmallPath smallPath = smallPathType.newInstance();
        smallPath.setVertexs(vertexsfff);
        // te.printAllWay();
        smallPath.setTopVertex((Vertex<DistancWeight>) smallPath.getVertexs()[0], DistancWeight.class);
        long startTim = new Date().getTime();
        smallPath.generateSmallPathToOther();
        long endTim = new Date().getTime();
        return vertexsfff;
    }

    public static void main(String[] args) throws Exception {

        Vertex<DistancWeight>[] res1 = geneateStrategy(SmallPathTop.class);
        Vertex<DistancWeight>[] res2 = geneateStrategy(Dijkstra.class);
        TopoUtils.isTwoStrategyEqual(res1, res2);

        for (int i = 0; i < res1.length; i++) {
            System.out.println("path from 0 to [" + res1[i].getIndex() + "]");
            res1[i].printPath();
            System.out.println();
            res2[i].printPath();
            System.out.println("---------------------");
            System.out.println("weight value is :" + (res1[i].getInWeight() == null ? "" : res1[i].getInWeight().getWeightValue()));
            System.out.println("weight value is :" + (res2[i].getInWeight() == null ? "" : res2[i].getInWeight().getWeightValue()));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (Vertex<DistancWeight> ve : res2) {
            System.out.println("path from 0 to [" + ve.getIndex() + "]");
            ve.printPath();
            System.out.print("weight value is :" + (ve.getInWeight() == null ? "" : ve.getInWeight().getWeightValue()));
            System.out.println("---------------");
        }
    }

}
