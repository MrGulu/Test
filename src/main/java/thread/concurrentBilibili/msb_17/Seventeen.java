package thread.concurrentBilibili.msb_17;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 * <p>
 * <p>
 * 所以说synchronized是锁在堆内存中真正new出来的对象，并不是栈内存的对象引用！
 */
public class Seventeen {
    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        Seventeen seventeen = new Seventeen();
        //启动第一个线程
        new Thread(seventeen::m, "thread1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建第二个线程
        Thread t2 = new Thread(seventeen::m, "thread2");
        //锁对象发生改变，所以t2线程得以执行，如果注释掉这句话，线程2将永远得不到执行机会
        seventeen.o = new Object();
        t2.start();

    }
}
