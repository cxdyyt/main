package Threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SyncAndReentrantLockTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ShareResource shareResource = new ShareResource();
        Thread t1 = new Thread(() -> {
            System.out.println("thread name:-------- " + Thread.currentThread().getName());
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    shareResource.print3();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    throw new RuntimeException();
                }
            }
        }, "thnn");
        t1.start();
        Thread t2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    shareResource.print2();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    throw new RuntimeException();
                }
            }
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    shareResource.print1();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    throw new RuntimeException();
                }
            }
        });
        t3.start();
        try {
            TimeUnit.MICROSECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}

class ShareResource {
    public ReentrantLock lock = new ReentrantLock();

    int flag = 1;

    Condition c3 = lock.newCondition();

    Condition c2 = lock.newCondition();

    Condition c1 = lock.newCondition();

    void print1() throws InterruptedException {
        try {
            lock.lockInterruptibly();
            while (flag != 1) {
                c1.await();
            }
            System.out.println("process 1");
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    void print2() throws InterruptedException {
        try {
            lock.lockInterruptibly();
            while (flag != 2) {
                c2.await();
            }
            System.out.println("process 2");
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    void print3() throws InterruptedException {
        try {
            lock.lockInterruptibly();
            while (flag != 3) {
                c3.await();
            }
            System.out.println("process 3");
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}
