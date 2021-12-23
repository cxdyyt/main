package Threads;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    static ReentrantLock rrrLock = new ReentrantLock();

    final ReentrantLock rrrLock1;

    final int i;
    {
        System.out.println();
    }

    public LockTest(ReentrantLock rrrLock1, int i) {
        super();
        this.rrrLock1 = rrrLock1;
        this.i = i;
    }

    public static void read() {
        new Thread(() -> {
            rrrLock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                rrrLock.unlock();
            }
        }, "lockTest").start();

    }

    public static void main(String[] args) {
        // int result = -1;
        // int target = 45;
        // int[] ints = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        // for (int left = 0, right = ints.length - 1, index = (left + right) / 2; index >= 0 &&
        // index <= ints.length - 1;) {
        //
        // if (ints[index] == target) {
        // result = index;
        // break;
        // } else if (target > ints[index]) {
        // left = index + 1;
        // } else {
        // right = index - 1;
        // }
        // index = (left + right) / 2;
        // }
        // System.out.println(result);

        Map<Integer, Integer> map = new HashMap<>();
        Random rd = new Random(48);
        for (int i = 0; i < 1000; i++) {
            map.put(rd.nextInt(1000), rd.nextInt(1000));
        }
        System.out.println(map);
        // Integer i = Integer.valueOf(232);
        // read();
        // rrrLock.lock();
        // try {
        //
        // } finally {
        // rrrLock.unlock();
        // }
        // Random random = new Random(47);
        // for (int i = 0; i < 100; i++) {
        // System.out.print(random.nextInt(100) + ",");
        // }
        // long dd = 200;
        // short m = 0b10101101;
        // System.out.println(0x011);
        // System.out.println(Integer.toOctalString(64));
        // System.out.println(0b11 ^ 0b11);
        // System.out.println("==========================");
        // System.out.println(~0b11110);
        // System.out.println((~0b11110) >>> 1);
        // System.out.println("===================");
        short sht = 4;
        // System.out.println(Integer.toBinaryString(sht));
        //
        // float fl = 1e+2f;
        System.out.println(sht >>> 2);
        System.out.println(sht);

    }
}
