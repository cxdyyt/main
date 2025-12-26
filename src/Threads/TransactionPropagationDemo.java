package Threads;

/**
 * 模拟 Spring 事务传播行为
 * 注意：这是纯 Java 模拟，为了演示概念，真实 Spring 环境需要 AOP 代理
 */
public class TransactionPropagationDemo {

    static class TransactionManager {
        static ThreadLocal<String> currentTx = new ThreadLocal<>();

        static void start(String name) {
            System.out.println("【TX】开启事务: " + name);
            currentTx.set(name);
        }

        static void commit(String name) {
            System.out.println("【TX】提交事务: " + name);
            if (currentTx.get().equals(name)) {
                currentTx.remove();
            }
        }

        static void rollback(String name) {
            System.out.println("【TX】回滚事务: " + name);
            if (currentTx.get().equals(name)) {
                currentTx.remove();
            }
        }
        
        static boolean hasTransaction() {
            return currentTx.get() != null;
        }
        
        static String getCurrent() {
            return currentTx.get();
        }
    }

    // 模拟 ServiceA
    static class ServiceA {
        ServiceB serviceB = new ServiceB();

        public void methodA_Required() {
            System.out.println("\n--- Case 1: REQUIRED (默认) ---");
            // A 开启事务
            TransactionManager.start("Tx-A");
            try {
                System.out.println("ServiceA 执行逻辑...");
                
                // 调用 B，B 发现有事务，直接加入，不开启新事务
                serviceB.methodB_Required();
                
                // 模拟异常
                // throw new RuntimeException("A 出错了");
                
                TransactionManager.commit("Tx-A");
            } catch (Exception e) {
                TransactionManager.rollback("Tx-A");
            }
        }
        
        public void methodA_RequiresNew() {
            System.out.println("\n--- Case 2: REQUIRES_NEW (新事务) ---");
            TransactionManager.start("Tx-A");
            try {
                System.out.println("ServiceA 执行逻辑 (Part 1)...");
                
                // 调用 B
                serviceB.methodB_RequiresNew();
                
                System.out.println("ServiceA 执行逻辑 (Part 2)...");
                
                // 假设这里 A 报错了
                throw new RuntimeException("A 在 B 提交后报错了");
                
            } catch (Exception e) {
                System.out.println("捕获异常: " + e.getMessage());
                TransactionManager.rollback("Tx-A");
            }
        }
    }

    // 模拟 ServiceB
    static class ServiceB {
        public void methodB_Required() {
            // 检查传播行为
            if (TransactionManager.hasTransaction()) {
                System.out.println("ServiceB: 检测到现有事务 " + TransactionManager.getCurrent() + "，加入其中");
            } else {
                TransactionManager.start("Tx-B");
            }
            
            System.out.println("ServiceB 执行逻辑...");
            // 注意：这里不执行 commit/rollback，因为是加入别人的事务
        }
        
        public void methodB_RequiresNew() {
            // 挂起外部事务（模拟）
            String outerTx = TransactionManager.getCurrent();
            System.out.println("ServiceB: 挂起外部事务 " + outerTx);
            TransactionManager.currentTx.remove(); // 暂时移除
            
            // 开启新事务
            TransactionManager.start("Tx-B-New");
            try {
                System.out.println("ServiceB (新事务) 执行逻辑...");
                TransactionManager.commit("Tx-B-New");
            } catch (Exception e) {
                TransactionManager.rollback("Tx-B-New");
            } finally {
                // 恢复外部事务
                System.out.println("ServiceB: 恢复外部事务 " + outerTx);
                TransactionManager.currentTx.set(outerTx);
            }
        }
    }

    public static void main(String[] args) {
        ServiceA a = new ServiceA();
        
        a.methodA_Required();
        
        a.methodA_RequiresNew();
    }
}
