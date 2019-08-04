package thread.concurrentBilibili.msb_23;

import java.util.Arrays;

/**
 * 线程安全的单例模式
 * <p>
 * http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * <p>
 * 更好的是采用下面的方式，既不用加锁，也能实现懒加载
 */
public class Singleton {

    private Singleton() {
        System.out.println("Singleton");
    }

    public static Singleton getInstance() {
        return Inner.s;
    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[200];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                Singleton.getInstance();
            });
        }

        Arrays.asList(threads).forEach(Thread::start);
    }
}
