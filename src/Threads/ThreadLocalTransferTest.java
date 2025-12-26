package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTransferTest {
    
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        // 创建一个只有1个线程的线程池，确保线程被复用
        ExecutorService executor = Executors.newFixedThreadPool(1);
        
        System.out.println("=== 1. 普通 ThreadLocal (无法传递) ===");
        threadLocal.set("我是主线程的数据");
        executor.submit(() -> {
            System.out.println("子线程读取普通ThreadLocal: " + threadLocal.get());
        });
        
        Thread.sleep(100); // 等待打印
        
        System.out.println("\n=== 2. InheritableThreadLocal (线程池复用会导致问题) ===");
        inheritableThreadLocal.set("数据版本-V1");
        
        // 第一次提交，线程池创建新线程，会拷贝 V1
        executor.submit(() -> {
            System.out.println("子线程(任务A)读取Inheritable: " + inheritableThreadLocal.get());
        });
        
        Thread.sleep(100);
        
        // 主线程修改数据为 V2
        inheritableThreadLocal.set("数据版本-V2");
        
        // 第二次提交，线程池复用旧线程，如果不做处理，它里面持有的还是 V1
        executor.submit(() -> {
            System.out.println("子线程(任务B)读取Inheritable: " + inheritableThreadLocal.get() + " (预期是V2，但实际可能是V1)");
        });
        
        Thread.sleep(100);

        System.out.println("\n=== 3. 手动显式传递 (推荐方案) ===");
        threadLocal.set("我是主线程的数据(手动传递)");
        
        // 在主线程获取值
        String valueToPass = threadLocal.get();
        
        executor.submit(() -> {
            // 在子线程中使用传递进来的值
            // 如果需要子线程的后续调用链也能用 threadLocal.get()，需要重新 set
            threadLocal.set(valueToPass);
            try {
                System.out.println("子线程(手动)读取: " + threadLocal.get());
            } finally {
                // 必须清理！否则线程复用时会污染下一个任务
                threadLocal.remove();
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
    }
}
