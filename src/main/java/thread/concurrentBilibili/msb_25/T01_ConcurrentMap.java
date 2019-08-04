package thread.concurrentBilibili.msb_25;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/498061.html
 * 阅读ConcurrentSkipListMap
 */
public class T01_ConcurrentMap {
    public static void main(String[] args) {
        //300ms左右
        //10个线程放1亿数据时，耗时20s，cpu 100%，哇。
        /**
         * 300ms左右
         * 10个线程放1亿数据时，耗时20s，cpu 100%，哇。
         */
        Map<String, String> map = new ConcurrentHashMap<>();

        /**
         * 450ms左右（ConcurrentSkipListMap，跳表结构，往里插入的时候效率也是较高的，并且是排好序的）
         * 在非并发的情况下，一个Map想排好顺序，使用TreeMap或者SortedMap，但是插入的效率很低，高并发的情况下就更低了，
         * 所以高并发情况，且需要插入数据排好序，就是用ConcurrentSkipListMap，插入的时候稍慢，查找很快。
         */
//        Map<String,String> map = new ConcurrentSkipListMap<>();

        /**
         * 500ms左右
         * 默认所有的方法都是加了锁的，但因为效率比较低，用的很少了。
         * Hashtable和HashMap都实现了Map接口，但是Hashtable的实现是基于Dictionary抽象类的。
         * Java5提供了ConcurrentHashMap，它是HashTable的替代，比HashTable的扩展性更好
         */
//        Map<String,String> map = new Hashtable<>();

        /**
         * 550ms左右
         */
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>()); //Collections.synchronizedXXX
//        TreeMap
        Random r = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                }
                latch.countDown();
            });
        }

        Arrays.asList(threads).forEach(Thread::start);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
