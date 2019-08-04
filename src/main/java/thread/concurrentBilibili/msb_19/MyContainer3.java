package thread.concurrentBilibili.msb_19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁。
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * 问题：
 * t2只有在t1结束后才能end；因为t1，只是notify，其不会释放锁，锁还是
 * 被t1占有，t2想占有锁的时候发现被t1占用。
 */
public class MyContainer3 {
    //添加volatile，使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer3 c = new MyContainer3();

        final Object lock = new Object();
        /**
         * 需要先启动t2，使其wait进入等待池，这里用if就可以，不需要用while，
         * 因为它只需要判断一次，醒了之后退出就可以了，一直阻塞。
         */
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 start");
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end");
            }
        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1 start");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                    if (c.size() == 5) {
                        lock.notify();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
