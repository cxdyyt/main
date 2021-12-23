package Threads;

public class ThreadDeadLock implements Runnable {
    String lock1 = "";

    String lock2 = "";

    public ThreadDeadLock(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "get " + lock1);
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "get " + lock2);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String l1 = "11";
        String l2 = "22";
        new Thread(new ThreadDeadLock(l1, l2)).start();
        // TimeUnit.SECONDS.sleep(1);
        new Thread(new ThreadDeadLock(l2, l1)).start();
    }
}
