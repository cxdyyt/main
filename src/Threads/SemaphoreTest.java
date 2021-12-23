package Threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    static Semaphore semaphore = new Semaphore(3);

    static void plactAt() {

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + " place 3 seconde");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " leave");
                }
            }, "" + i).start();
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        plactAt();
    }

}
