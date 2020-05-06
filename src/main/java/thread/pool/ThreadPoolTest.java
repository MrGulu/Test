package thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author tangwenlong
 * @date 2020-04-22
 * @description 构造参数
 * int corePoolSize,
 * int maximumPoolSize,
 * long keepAliveTime,
 * TimeUnit unit,
 * BlockingQueue<Runnable> workQueue,
 * ThreadFactory threadFactory,
 * RejectedExecutionHandler handler
 * <p>
 * 假如corePoolSize=5，maximumPoolSize=50，queueCapicity=100
 * 那么当往线程池中派送任务时，在还没有执行完一条任务时（模拟时使用sleep睡眠）
 * 如果for循环派送 <= corePoolSize+queueCapicity=5+100=105时，
 * 线程池中只创建了核心线程，并没有其他线程创建，即使队列中排满了任务。
 * 如果for循环派送 > corePoolSize+queueCapicity=5+100=105，但 <= maximumPoolSize+queueCapicity=50+100=150时，
 * 线程池中除了核心线程，从106开始额外创建第一个线程（核心线程处理5个，并且队列中满了100个任务，队列放不下），直至达到最大
 * 线程限制maximumPoolSize=50，也就是额外创建45个线程。
 * 如果for循环派送 > maximumPoolSize+queueCapicity=50+100=150时，队列满，并且达到了最大线程数限制maximumPoolSize=50，
 * 此时如果再有任务派送到线程池当中，则会触发配置的拒绝策略！！！
 * <p>
 * <p>
 * 拒绝策略：
 * AbortPolicy()
 * 丢弃任务，抛RejectedExecutionException异常
 * DiscardPolicy()
 * 丢弃任务，不抛异常
 * DiscardOldestPolicy()
 * 抛弃队列最前面的任务，最老的任务，新任务入队
 * CallerRunsPolicy()
 * 由调用线程直接执行任务
 */
public class ThreadPoolTest {
    /**
     * 值得注意的事有两点
     * 1, taskExecutor.execute(new ThreadTransCode());
     * 激活的线程都是守护线程，主线程结束，守护线程就会放弃执行，这个在业务中式符合逻辑的，在单元测试中为了看到执行效果，需要自行阻塞主线程。
     * 2, taskExecutor.execute(new ThreadTransCode());
     * 执行也不是完全安全的，在执行的过程中可能会因为需要的线程查过了线程队列的容量而抛出运行时异常，如有必要需要捕获
     */

    /**
     * @description 原生线程池测试
     */
    @Test
    public void test1() {
        int num = 150;
        CountDownLatch latch = new CountDownLatch(num);

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("AbortPolicy-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(5, 50,
                300L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//                new LinkedBlockingQueue<Runnable>(100), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
//                new LinkedBlockingQueue<Runnable>(100), namedThreadFactory, new ThreadPoolExecutor.DiscardOldestPolicy();
//                new LinkedBlockingQueue<Runnable>(100), namedThreadFactory, new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < num; i++) {
            System.out.println(i);
            singleThreadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            });
        }
        //关闭线程池
        singleThreadPool.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 使用xml配置spring的异步线程池
     */
    @Test
    public void test2() {
        int num = 150;
        CountDownLatch latch = new CountDownLatch(num);
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:threadPool.xml");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor) context.getBean("threadPool");
        for (int i = 0; i < num; i++) {
            System.out.println(i);
            threadPoolTaskExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
