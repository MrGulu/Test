package thread.concurrentBilibili.msb_26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * newCachedThreadPool
 * 刚开始一个线程都没有，来一个任务开一个线程，来第二个任务开第二个线程，如果这时候第三个任务来了，
 * 而这时候之前的任务恰巧空闲了（还未到销毁时间），那么直接让空闲的线程来执行任务。如果又来了一个任务，
 * 但是无空闲线程，那么就再启动一个线程…… 一直到服务器支撑不了，所以是没有上限的，一般来说支撑几万个线程
 * 是没问题的。
 * 默认空闲超过60s，就自动销毁，可指定。
 */
public class T08_CachedPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        //80s,是来看60s是否销毁
        try {
            TimeUnit.SECONDS.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(service);

        service.shutdown();
    }
}
