package thread.concurrentBilibili.msb_22;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在于ThreadLocal中，避免synchronized的使用。
 * <p>
 * 如果在使用的时候，自己线程的改变，自己维护状态，不用通知其他线程，那么这个时候可以
 * 使用ThreadLocal。
 * <p>
 * ThreadLocal可能会导致内存泄漏（百度一下，比较麻烦）
 */
public class ThreadLocal2 {
    /*按理说，在第二个线程set了person，在第一个线程应该可以取到同一个对象，但事实并不是这样的~！
     * ThreadLocal是线程局部变量，它不是共享的，每个线程有它自己的一份值。与其它线程之间不影响。
     *
     * 自己的线程自己用，别的线程你要用，你自己放。
     *
     * 不用ThreadLocal，如果两个线程访问同一个对象，只能上 锁；但是使用ThreadLocal，相当于每个
     * 线程都有自己的变量，互相之间不会产生任何冲突，改的那个和放的那个两个之间没有影响，可以简单的
     * 理解为，这个对象，每个线程拷贝了一份，改的都是自己那份。
     * */
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }

    static class Person {
        String name = "zhangsan";
    }
}


