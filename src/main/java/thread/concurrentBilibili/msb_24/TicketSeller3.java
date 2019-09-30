package thread.concurrentBilibili.msb_24;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {
    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号： " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    /**
                     * 相当于将判断和操作，加入了一个原子操作当中，肯定不会出问题。
                     * 但是加锁效率并不是特别高，因为只有一个线程释放锁之后，另一个
                     * 线程才能进入。每销售一张票都要把整个队列锁定。
                     * 因为每张票休眠10ms，所以十万张票总共需要大约10s左右，而使用Queue时，<1s级就完成了！
                     */
                    synchronized (tickets) {
                        if (tickets.size() <= 0) {
                            break;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("sale--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
