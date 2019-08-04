package thread.concurrentBilibili.msb_25;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 同步队列，一种特殊的TransferQueue
 * 容量为0，不同于TransferQueue，调用add或者put的时候没有消费者，会先放入队列中，等有了消费者之后，
 * 消费者再到队列中取。
 * 但是SynchronousQueue容量为0，放入之后必须立马给我消费掉，否则就出问题。
 * 因为容量为0，所以调用add是会抛出异常的:Queue full
 * 所以必须调用put方法。
 */
public class T09_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");//阻塞等待消费者消费
//        strs.add("aaa");
        System.out.println(strs.size());
    }
}
