package thread.concurrentBilibili.msb_22;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 */
public class ThreadLocal1 {
    //加volatile肯定不出问题，不加volatile可能不出问题。
    private volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        }).start();
    }
}

class Person {
    String name = "zhangsan";
}
