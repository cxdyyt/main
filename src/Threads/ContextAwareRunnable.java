package Threads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * 这是一个通用的线程上下文传递工具
 * 它可以自动把当前线程的 ThreadLocal 值捕获，并“搬运”到子线程中
 */
public class ContextAwareRunnable implements Runnable {

    private final Runnable target;
    private final String capturedValue; // 这里以 String 为例，实际可以是 Object 或 Map

    // 假设这是我们需要传递的核心 ThreadLocal
    public static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    // 1. 在【主线程】创建任务对象时，自动捕获当前的 Context
    public ContextAwareRunnable(Runnable target) {
        this.target = target;
        this.capturedValue = CONTEXT_HOLDER.get(); 
    }

    @Override
    public void run() {
        // 2. 在【子线程】开始执行时：
        // 备份子线程原本可能存在的脏数据
        String backup = CONTEXT_HOLDER.get();
        
        try {
            // 3. 注入主线程传递过来的值
            CONTEXT_HOLDER.set(capturedValue);
            
            // 4. 执行真正的业务逻辑
            target.run();
            
        } finally {
            // 5. 【关键】还原现场，保证线程归还给线程池时是干净的
            if (backup != null) {
                CONTEXT_HOLDER.set(backup);
            } else {
                CONTEXT_HOLDER.remove();
            }
        }
    }

    // --- 方便使用的静态工具方法 ---

    /**
     * 装饰一个 Runnable，使其具备上下文传递能力
     */
    public static Runnable wrap(Runnable task) {
        return new ContextAwareRunnable(task);
    }
    
    /**
     * 装饰一个 Executor，让它提交的所有任务自动具备能力
     */
    public static Executor wrap(Executor executor) {
        return (command) -> executor.execute(new ContextAwareRunnable(command));
    }
}
