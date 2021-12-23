package Threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefineExecutorService {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(3);
        ExecutorService es = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, workQueue, Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 9; i++) {
            es.submit(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        es.shutdown();

    }

}
