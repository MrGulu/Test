package thread.concurrentBilibili.msb_20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应，
 * 在一个线程等待锁的过程中，可以被打断
 */

/**
 * lock 与 lockInterruptibly比较区别在于：
 * lock                优先考虑获取锁，待获取锁成功后，才响应中断。
 * lockInterruptibly   优先考虑响应中断，而不是响应锁的普通获取或重入获取。
 * <p>
 * 详细区别：
 * ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread.interrupt方法来中断等待线程的等待而直接返回，这时不用获取锁，而会抛出一个InterruptedException。
 * ReentrantLock.lock方法不允许Thread.interrupt中断,即使检测到Thread.isInterrupted,一样会继续尝试获取锁，失败则继续休眠。只是在最后获取锁成功后再把当前线程置为interrupted状态,然后再中断线程。
 * ————————————————
 * 版权声明：本文为CSDN博主「yyd19921214」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/yyd19921214/article/details/49737061
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
         *
         * 注意这里t2线程执行lock.lockInterruptibly()，是如果其他线程（本例中是主线程）发出了
         * 中断信号，就优先响应中断请求，t2抛出异常，中断执行！
         * 如果是lock.lock()，那么即使在其他线程中发出了中断信号，t2也一直处于不断获取锁的过程中，
         * 只有获取锁成功的时候，才会响应中断信号！
         */
        //打断线程2的等待
        t2.interrupt();
    }
}
