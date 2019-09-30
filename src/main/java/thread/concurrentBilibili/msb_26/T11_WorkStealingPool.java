package thread.concurrentBilibili.msb_26;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 工作窃取的线程池？底层使用ForkJoinPool实现。
 * <p>
 * 每个线程都维护自己的一个队列，当一个线程执行完自己队列的任务时，会到其他线程维护的
 * 队列中，偷任务来执行。主动找活干。
 */
public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        //看CPU多少核,newWorkStealingPool它默认就会给你启这些个线程
        System.out.println(Runtime.getRuntime().availableProcessors());
        /**
         * 由于电脑是8核的，所以下面加入9个任务测试
         * 第一个任务交给第一个，第二个交给第二个……
         * 那么第九个就会在第一个任务完成后由第一个线程执行。
         */
        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(1000));
        service.execute(new R(1000));
        service.execute(new R(1000));
        service.execute(new R(2000));

        //由于产生的是精灵线程（守护线程daemon、后台线程），主线程不阻塞的话，看不到输出
        System.in.read();
    }

    static class R implements Runnable {

        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }

}
