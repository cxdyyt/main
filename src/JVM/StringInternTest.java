package JVM;

public class StringInternTest {

    static void iPlusTest() {
        int i = 1;
        int j = i++;
        int m = ++i;
        System.out.println(i);
        System.out.println(j);
        System.out.println(m);
    }

    int getInt(int i, GCTest j) {
        return 8;
    }

    GCTest getIntGCTest(GCTest j) {
        return j;
    }

    public static void main(String[] args) {
        // String xxx = "66" + "88";
        // String m = "aa";
        // String str0 = new String("java") + "11";
        String str0 = new String("java11555");
        String str1 = "java11555";
        System.out.println(str0 == str0.intern());// false
        System.out.println(str0 == str1);// false
        System.out.println(str1 == str0.intern());// true

        // String str1 = new StringBuilder("eee").append("22").toString();
        // String str1 = new StringBuilder("eee").toString();

        // String str1 = "eee" + "22";

        // System.out.println(str1 == str1.intern());
        //
        // String str3 = new String("eee22");
        // System.out.println(str1 == str3);
        //
        // String str2 = new StringBuilder("ja").append("va").toString();
        // System.out.println(str2.intern() == str2);
        // String s1 = new String("123").intern();
        // String s2 = "123";
        // System.out.println(s1 == s2); // true
        // String s1 = "23";
        /*
         * 这里执行时，常量“1” 会首先到字符串常量池里面去找，如果没有就创建一个，并且加入字符串常量池。 得到的123结果对象，不会存入到常量池。这里特别注意和两个常量字符串相加不同
         * “1”+“23” 参考上面第三点 由于不会进入常量池，所以s2 和 s3 常量池地址值不同，所以输出为false
         */
        // String s2 = "1" + s1;
        // String s3 = "123";
        // System.out.println(s2 == s3.intern());

        // String s1 = new String("1") + new String("23");
        //// s1.intern();
        // String s2 = "123";
        // System.out.println(s1 == s2);
        iPlusTest();
    }
}
