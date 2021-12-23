package JVM;

import java.util.WeakHashMap;

public class JVMCommonStudy {
    RuntimeTest runtimeTest = new RuntimeTest();

    public static void main(String[] args) {
        // List<Integer> list = Arrays.asList(1, 2, 3, 4);
        // int sum = 0;
        // for (int i : list) {
        // sum += i;
        // }
        // // System.out.println(sum);
        //
        // Integer a = 1;
        // Integer b = 2;
        Integer c = new Integer(127);
        Integer d = new Integer(127);
        Integer e = 321;
        Integer f = 321;
        // Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        // System.out.println(c == (a + b));
        // System.out.println(c.equals(a + b));
        // System.out.println(g == (a + b));
        // System.out.println(g.equals(a + b));
        Integer xx = 10;
        Integer yy = 99;
        new JVMCommonStudy().method1(xx, yy);
        System.out.println("------------" + xx);
    }

    public void method1(Integer ini, Integer dd) {
        String ddj = ini.toString();
        dd = null;
        Integer a = 1;
        Integer b = 1;
        System.out.println("a==b: " + (a == b));
        int i = 0;
        for (; i <= 10; i++) {

        }
        System.out.println(2 % 2);

        WeakHashMap<Integer, Integer> map = new WeakHashMap<>();
        map.put(a, 123);
        System.gc();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Integer ddd = 32323;
        System.out.println(map);
    }

}
