package thread.concurrentBilibili.msb_19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行，整个通信过程比较繁琐
 */
public class MyContainer4 {
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
         * 因为它只需要判断一次，醒了之后退出就可以了。
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
                //通知t1继续执行
                lock.notify();
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
                        //释放锁，让t2得以运行，在t2运行完之后再notify，通知t1继续往下执行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
