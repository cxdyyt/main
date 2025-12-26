package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DecoratorDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        
        // 1. 设置主线程上下文
        ContextAwareRunnable.CONTEXT_HOLDER.set("LOGIN_USER: Admin");
        
        System.out.println("主线程 Context: " + ContextAwareRunnable.CONTEXT_HOLDER.get());
        
        // 2. 提交任务时，使用 wrap() 包装一下
        // 哪怕这个线程池是旧的、脏的，wrap 也能保证把当前值传进去
        pool.submit(ContextAwareRunnable.wrap(() -> {
            System.out.println("子线程 Context: " + ContextAwareRunnable.CONTEXT_HOLDER.get());
            
            // 模拟业务逻辑修改
            ContextAwareRunnable.CONTEXT_HOLDER.set("LOGIN_USER: ChangedInSubThread");
        }));
        
        Thread.sleep(100);
        
        // 3. 验证主线程不受影响
        System.out.println("主线程 Context (复原检查): " + ContextAwareRunnable.CONTEXT_HOLDER.get());
        
        // 4. 验证子线程没有污染线程池（再次提交一个不带 context 的任务）
        ContextAwareRunnable.CONTEXT_HOLDER.remove();
        pool.submit(ContextAwareRunnable.wrap(() -> {
            System.out.println("子线程(第二次) Context: " + ContextAwareRunnable.CONTEXT_HOLDER.get());
        }));
        
        pool.shutdown();
    }
}
