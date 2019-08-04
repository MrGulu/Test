package thread.concurrentBilibili.msb_25;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器CopyOnWrite
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 */
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists =
                //new ArrayList<>();//这个会出并发问题！最后打印lists.size时不是10000
                //new Vector<>();//并发访问一致，线程安全
                /**
                 * 读的时候不用加锁，比如事件监听器，某个按钮维护了一个事件监听器队列，
                 * 每个事件监听器都会去读是否被触发，所以读的很多，但是往里加新的监听器很少。
                 * 修改是复制一份新的，在新的上面修改，原来的不会变，因此读原来数据的线程不用加任何锁。
                 */
                new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] threads = new Thread[100];
        for (int i = 0, n = threads.length; i < n; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    lists.add("a" + r.nextInt(10000));
                }
            });
        }
        runAndComputeTime(threads);
        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] threads) {
        long s1 = System.currentTimeMillis();
        Arrays.asList(threads).forEach(Thread::start);
        Arrays.asList(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }

}
