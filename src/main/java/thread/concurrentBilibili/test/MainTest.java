package thread.concurrentBilibili.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainTest {

    private static List<Integer> list = new ArrayList<>();
//    private volatile List<Integer> list = new ArrayList<>();
//    private List<Integer> list = new ArrayList<>();

    private Random r = new Random();

    /**
     * 1.下面10个线程添加10w数据，最后的size！=100000
     * 而且有时运行会发生异常：
     * Exception in thread "t2" java.lang.ArrayIndexOutOfBoundsException: 6246
     * at java.util.ArrayList.add(ArrayList.java:459)
     * at thread.concurrentBilibili.test.MainTest.lambda$test1$0(MainTest.java:29)
     * at java.lang.Thread.run(Thread.java:745)
     * 发生的原因就是，因为list是用数组实现的，达到size时，会扩容到原来的1.5倍大小，而多个线程同时操作
     * ArrayList时，会发生并发问题！在一个线程添加元素达到size时，还没来得及扩容，另一个线程往里添加元素，
     * 就会发生上述异常。
     * 解决方法：①使用Vector等同步容器②加锁
     * <p>
     * 2.即使是使用volatile修饰，也同样会发生问题，因为volatile只保证可见性，不保证原子性。
     * <p>
     * 3.static修饰的对象，只是保证该变量是静态的，没有副本。
     * <p>
     * 4.static只是表示这个变量是该类所有实例共享的。volatile是表示变量在使用时直接去共享内存中获取，
     * 而不是从当前线程的私有内存区域存取。通常在线程开启时，会将使用到的变量产生一个线程内的副本，
     * 与主线程内存中的数据不同步。
     */
    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    list.add(r.nextInt(1000));
                    /*synchronized (list) {
                        list.add(r.nextInt(1000));
                    }*/
                }
            }, "t" + i).start();
        }
        System.out.println(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
