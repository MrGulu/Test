package thread.concurrentBilibili.msb_26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 扔任务的时候维护一个任务列表队列，同样还维护一个completed tasks队列，完成的就扔进去。
 */
public class T05_ThreadPool {
    public static void main(String[] args) {
        //个数固定的线程池
        ExecutorService service = Executors.newFixedThreadPool(5);//execute submit
        for (int i = 0; i < 6; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        /**
         * java.util.concurrent.ThreadPoolExecutor@22927a81[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
         * 因为线程池的size=5，所以当放入第六个线程时，就放入队列等待
         */
        System.out.println(service);

        //正常的关闭，会等会有任务都执行完才会彻底关闭，所以isTerminated为false；isShutdown为true
        //还有一种方法，service.shutdownNow();不管任务是否都执行完，直接全部关闭。
        service.shutdown();
        /**
         * 注意shutting down，正在关闭。
         * false
         * true
         * java.util.concurrent.ThreadPoolExecutor@22927a81[Shutting down, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
         */
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * Terminated代表正常关闭。
         * true
         * true
         * java.util.concurrent.ThreadPoolExecutor@22927a81[Terminated, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 6]
         */
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}
