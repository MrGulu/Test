package thread.threadPractice;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//@SuppressWarnings("all")
public class TreadTest1 {
    int conut;
    static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger();
        count.compareAndSet(5, 6);
    }
}
