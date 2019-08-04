package thread.concurrentBilibili.msb_13;

import java.util.ArrayList;
import java.util.List;

/**
 * 最后打印的count的值是5w左右的数值；
 * 原因：
 * 假如说一个线程加到了100，这时2和3线程同时获取值开始进行++操作，加到101，2线程写回到主存，
 * 这时是101，但是3线程也是加到101，写回的时候是把101再覆盖了一遍，所以这样最后数要远小于100000.
 * <p>
 * <p>
 * <p>
 * 如果改成synchronized实现，就是去掉volatile关键字，然后将m方法加锁，一定能实现最后结果为100000
 */
public class Thirteen {
    private /*volatile*/ int count;

    synchronized void m() {
        for (int i = 0; i < 10000; i++) {
            //++操作不保证原子性
            count++;
        }
    }

    public static void main(String[] args) {
        Thirteen thirteen = new Thirteen();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(thirteen::m));
        }
        threads.forEach(thread -> thread.start());
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(thirteen.count);
    }
}
