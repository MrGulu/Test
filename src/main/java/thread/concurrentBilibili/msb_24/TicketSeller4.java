package thread.concurrentBilibili.msb_24;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号： " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    //poll从head拿，放的时候放到tail；
                    //如果队列空了，拿不到，返回值也是null，所以就可以像下面这样简单写
                    //Queue里是不能放null的，哪怕可以，在放的时候也要校验，不能将null放进去。
                    //poll是原子性的。

                    /**
                     * 本例中，操作和判断也是分离的，但是并不会出问题，原因如下：
                     *
                     * 先取再判断，在对队列判断之后，再也没有对队列做修改操作，
                     * 像之前的例子，在对队列做判断之后，做了修改操作（remove），而现在是先取再判断。
                     * 最多出问题是：
                     * String s = tickets.poll();
                     * if (s == null)
                     * 这两句之间被打断了，另一个线程队列拿空了，大不了返回头再拿一遍（while(true)）
                     * 另外poll底层实现是CAS，不加锁，效率很高。
                     */
                    String s = tickets.poll();
                    if (s == null) {
                        break;
                    } else {
                        System.out.println("sale--" + s);
                    }
                }
            }).start();
        }
    }
}
