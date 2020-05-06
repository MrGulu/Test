package thread.semaphore;

import org.junit.Test;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @author tangwenlong
 * @description 信号量学习
 * 限流器-100个线程获取资源（最多允许10线程同时进入来获取10个资源）
 * @date 2020-04-15
 */
public class SemaphoreTest {

    @Test
    @SuppressWarnings("all")
    public void test1() throws InterruptedException {
        ObjPool<Long, String> pool = new ObjPool<>(10, (long) 2);
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                String s = null;
                try {
                    s = pool.exec(aLong -> aLong.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 获取值:" + s);
            }, "thread-" + i);
            thread.start();
        }

    }

    class ObjPool<T, R> {
        final List<T> pool;

        final Semaphore sem;

        ObjPool(int size, T t) {
            pool = new Vector<>();
            for (int i = 0; i < size; i++) {
                pool.add(t);
            }
            sem = new Semaphore(size);
        }

        R exec(Function<T, R> function) throws InterruptedException {
            T t = null;
            sem.acquire();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取临界资源");
                t = pool.remove(0);
                return function.apply(t);
            } finally {
                System.out.println(Thread.currentThread().getName() + " 释放临界资源");
                pool.add(t);
                sem.release();
            }
        }
    }

}
