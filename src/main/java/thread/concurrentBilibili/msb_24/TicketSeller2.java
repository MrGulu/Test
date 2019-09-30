package thread.concurrentBilibili.msb_24;

import java.util.Vector;

public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号： " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    /**
                     * 判断和操作分离了！如果判断和操作之间没有任何其它操作，运行是没有问题的，
                     * 但是一般来说实际开发中，很可能中间有其他判断或者逻辑代码，如果在下面将
                     * sleep放开，会发生跟1中同样的问题！
                     * 虽然判断是原子性的，remove是原子性的，但在它俩之间的这段过程A线程执行时，
                     * 还没有remove的时候，还有可能被B线程打断，然后B线程remove，那么当A线程
                     * 继续执行时就触发错误了。
                     *
                     * 因为size方法执行完毕之后，锁就释放了，这时候其他线程就可以执行size方法，
                     * 而上一个线程往下走的时候，另一个线程也往下走，这时候可能就出现问题了！！！
                     *
                     * 当剩下最后一张票时，其中一个线程判断size>0，其它线程在中间这段代码执行时，
                     * 是有可能打断它的，然后remove，最后当那个线程再执行remove操作时，就触发了错误
                     * java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0
                     */
                    /*try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/

                    System.out.println("sale--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
