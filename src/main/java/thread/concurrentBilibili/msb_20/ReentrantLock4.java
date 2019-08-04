package thread.concurrentBilibili.msb_20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以怼线程interrupt方法做出响应，
 * 在一个线程等待锁的过程中，可以被打断
 */
public class ReentrantLock4 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted t1!");
//                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
//                lock.lock();
                lock.lockInterruptibly();//可以对interrupt()方法做出响应
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted t2!");
//                e.printStackTrace();
            } finally {
                //如果直接执行lock.unlock()，则会抛出IllegalMonitorStateException异常，因为t2没有获取到锁
//                lock.unlock();
                if (lock.tryLock()) {
                    lock.unlock();
                }
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * t1已经开始，而且是不停的运行，这时候上面的t2一直拿不到锁就会一直等待，这时候不想让
         * t2等待了，就打断它（主线程打断它）。如果使用lock.lock()是不会打断它的。
         * 就是lock()不响应中断，lockInterruptibly()响应中断。
         */
        //打断线程2的等待
        t2.interrupt();
    }
}
