package thread.concurrentBilibili.msb_12;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 * <p>
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 * <p>
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 * <p>
 * 可以阅读这篇文章进行更深入的理解
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * <p>
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * synchronized既保证可见性，又保证原子性
 * volatile只保证可见性，不保证原子性
 * 在只需要保证可见性的情况下，用volatile
 * <p>
 * <p>
 * 并不是每次判断while循环条件的时候都去主内存中获取一遍值，而是如果发现了主内存中的值被改变了，就通知用到它的线程，
 * 去从主内存中重新获取该值。
 * <p>
 * <p>
 * 如果while循环中使用sleep方法或者有sout语句，那么这时候会认为cpu是比较空闲的，
 * 是有可能也会去主内存中重新读一下该值，即使未被volatile修饰，不过这种情况while中的语句
 * 已经执行过很多很多遍了！
 * <p>
 * <p>
 * 如果不用volatile，只能用synchronized，但是用synchronized是重型锁，效率会低很多。
 * 能用volatile就不用synchronized，虽然java8中对其进行了改进，但还是比volatile重很多，
 * volatile也称为无锁同步，保证了线程之间数据的可见性。
 */
public class Twelve {
    //对比一下有无volatile的情况下，整个程序运行结果的区别
    /*volatile*/ boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {
            System.out.println("1");
//            try {
//                TimeUnit.MILLISECONDS.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        Twelve twelve = new Twelve();

        new Thread(twelve::m, "thread 1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        twelve.running = false;
    }
}
