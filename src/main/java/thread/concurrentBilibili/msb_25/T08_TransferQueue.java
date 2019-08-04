package thread.concurrentBilibili.msb_25;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 使用情况：
 * 类似于消息生产者发消息时，不往队列里扔，直接就去找消费者，找到消费者之后直接将消息交给消费者来消费；
 * 这样比扔到队列里，消费者再从队列中取消息消费效率要高，也就意味着并发能力更高。
 * 但是必须先启动消费者线程，否则transfer就会阻塞！！！························
 * <p>
 * 扔了你就必须给我赶紧处理掉，多用于一些实时消息处理。比如Netty，用的LinkedTransferQueue比较多。
 */
public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //如果没有消费者线程，调用transfer就会一直阻塞在这，比如调用transfer后再启动消费者
        strs.transfer("aaa");

        //使用下面两种方法都不会阻塞，只有使用transfer时会阻塞
//        strs.put("aaa");
//        strs.add("aaa");

        /*new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}
