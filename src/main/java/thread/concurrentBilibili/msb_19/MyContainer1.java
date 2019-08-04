package thread.concurrentBilibili.msb_19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * * 曾经的面试题（淘宝？）
 * * 实现一个容器，提供两个方法，add，size
 * * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * <p>
 * 本例并不能实现功能，线程1添加10个容器，无下文了。
 * 因为lists并没有被volatile修饰，这样的话，线程1在添加元素到lists时，并不一定什么时候写回主存，
 * 可能在写回主存的时候线程t2获取到size>5了，这时候线程t2就会一直无限循环下去。
 * 线程2并不能读取到size=5的情况
 * <p>
 * 但是如果在线程2，if判断的位置打断点，这样就会正确运行，因为打了断点，猜测是线程1执行添加元素时，
 * cpu空闲，然后写入主存，这时候继续运行断点，就会拿到值。
 */
public class MyContainer1 {

    List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer1 c = new MyContainer1();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();
    }
}
