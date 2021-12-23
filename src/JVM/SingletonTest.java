package JVM;

public class SingletonTest {
    private static SingletonTest instance = new SingletonTest();

    private SingletonTest() {
        System.out.println(Thread.currentThread().getName() + "create instance");
    }

    public static SingletonTest getInstance() {
        System.out.println(Thread.currentThread().getName());
        return instance;
    }

    public static void main(String[] args) {
        // System.out.println(SingletonTest.getInstance() == SingletonTest.getInstance());
        // System.out.println(SingletonTest.getInstance() == SingletonTest.getInstance());
        // System.out.println(SingletonTest.getInstance() == SingletonTest.getInstance());
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                InnerStaticClassSingleton.getInstance();
            }).start();
        }
    }

    public static class DCLSingleton {
        private static volatile DCLSingleton instance = null;

        private DCLSingleton() {
            System.out.println(Thread.currentThread().getName() + ": create instance");
        }

        public static DCLSingleton getInstance() {
            if (instance == null) {
                synchronized (DCLSingleton.class) {
                    if (instance == null) {
                        instance = new DCLSingleton();
                    }
                }
            }
            return instance;
        }

    }

    public static class InnerStaticClassSingleton {

        private InnerStaticClassSingleton() {
            System.out.println(Thread.currentThread().getName() + ": create instance");
        }

        private static class InstanceContainer {
            static InnerStaticClassSingleton instance = new InnerStaticClassSingleton();
        }

        public static InnerStaticClassSingleton getInstance() {
            return InstanceContainer.instance;
        }

    }

}
