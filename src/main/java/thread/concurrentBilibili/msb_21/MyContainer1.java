package thread.concurrentBilibili.msb_21;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 同步容器：如果满了，就不能再生产（wait）；如果空了，就不能再取（wait）。
 * 线程池中很多用的都是同步容器，BlockingQueue
 * <p>
 * 使用wait和notify/notifyAll来实现
 */
public class MyContainer1<T> {
    private final LinkedList<T> lists = new LinkedList<>();
    //最多10个元素
    private final int MAX = 10;
    private int count = 0;

    //想想为什么用while而不用if？

    /**
     * while 基本上 会和wait进行搭配使用，而不是if
     * 理由：
     * 假如在本例中，最大容量MAX=10，如果两个生产者线程同时判断lists.size()==MAX时，
     * 都进入了wait等待队列进行等待，这是消费者线程叫醒了生产者线程，假如其中一个醒了
     * put一个元素之后，容器满了！
     * 这时候又叫醒了另外一个容器，由于wait醒了之后，会接着上次的地方继续往下执行，又因为
     * 使用的是if判断，所以它不会再次判断lists.size()==MAX，所以这时再往里put元素，就会
     * 出问题！
     * 而使用while，哪怕其中一个put之后容器满了，叫醒另一个线程继续往下执行，但是它会再次判断
     * lists.size()==MAX，所以这样就不会导致满了又放这种错误！
     */
    public synchronized void put(T t) {
        while (lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        //通知消费者线程进行消费
        //这里不能使用notify，因为可能叫醒本身。而lists.size()==MAX，然后wait了，这时就阻塞了！
        this.notifyAll();
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        //通知生产者进行生产
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "consumer" + i).start();
        }
        //主线程休眠2s，让生产者和消费者线程能执行完，或者使用join方法；
        //生产者生产了50个，消费者消费50个。
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + "-" + j);
                }
            }, "producer" + i).start();
        }
    }
}
