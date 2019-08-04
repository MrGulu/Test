package thread.concurrentBilibili.msb_15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 */
public class Fifteen {
    private AtomicInteger count = new AtomicInteger(0);

    synchronized void m() {
        for (int i = 0; i < 10000; i++) {
            /**
             * 内部实现是用很底层的方法实现的。
             * 该操作不可分，在一个线程执行该步操作时，其他线程无法在内部打断它
             */
            /**
             * 但是不能保证多个方法连续调用是原子性的
             * 如下所示，get是原子性，incrementAndGet也是原子性
             * 但是这两个方法放在一起就不具备原子性，在要执行get时如果count值是小于1000的，这时其他线程将
             * count的值加到超过1000，那么此时get到的值就不满足条件。
             */
            //if count.get() < 1000
            count.incrementAndGet();//替代count++
        }
    }

    public static void main(String[] args) {
        Fifteen fifteen = new Fifteen();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(fifteen::m));
        }
        threads.forEach(thread -> thread.start());
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(fifteen.count);
    }
}
