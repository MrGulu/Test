package thread.concurrentBilibili.msb_20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantlock可以进行“尝试锁定”trylock，这样无法锁定，或者在指定时间内无法锁定，
 * 线程可以决定是否继续等待
 */

/**
 * lock不能写在方法上（想一下，如果写在方法上，到哪去释放锁呢。）
 */
public class ReentrantLock3 {
    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用trylock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据trylock的返回值来判定是否锁定
     * 也可以指定trylock的时间，由于trylock（time）抛出异常，所以要注意unlock的处理，必须放到finally中
     */
    void m2() {
        //下面不管锁没锁定都执行了，正常来说，应该锁定怎么样，没锁定怎么样，具体一些。
/*        boolean locked = lock.tryLock();
        System.out.println("m2……" + locked);
        if (locked) {
            lock.unlock();
        }*/
        boolean locked = false;
        try {
            //这里等待几秒，其实就是m1中打印到几（因为主线程中还休眠了1s，才启动第二个线程）
            //如果获取不到锁，就继续往下执行；不想lock方法，如果获取不到，会一直等，直到海枯石烂
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            System.out.println("m2……" + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock3 r1 = new ReentrantLock3();
        new Thread(r1::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r1::m2).start();
    }
}
