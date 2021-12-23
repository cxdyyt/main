package JVM;

import java.util.concurrent.locks.ReentrantLock;

public class TryCatchTest {
    String memberStr = "dawd";

    JVMCommonStudy jVMStudy1 = new JVMCommonStudy();

    static JVMCommonStudy jVMStudy2 = new JVMCommonStudy();

    static Integer inter = 23;

    static int test1() {
        int i = 1;
        try {
            return 3;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 4;
        } finally {
            i = 8;
            return i;
        }
    }

    static int test2() {
        int i = 2;
        i = ++i;
        // int j = i++;
        return i;
    }

    static void test3() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }

    static void test4() {
        ReentrantLock lock = new ReentrantLock();
        int i = 0;
        lock.lock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(test1());
        // test3();
    }

}
