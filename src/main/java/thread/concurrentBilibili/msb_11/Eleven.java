package thread.concurrentBilibili.msb_11;

import custom.exception.BusinessException;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，默认情况锁会被释放。
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况。
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适，
 * 在第一个线程中抛出异常，其他线程就会进入同步代码块，有可能会访问到异常产生时的数据。
 * 因此要非常小心的处理同步业务逻辑中的异常。
 * <p>
 * <p>
 * 如果不想释放锁，那么就用try catch块处理，继续循环。
 * 或者捕获到异常之后，进行事务的回滚，同时通过抛出自定义异常，释放掉锁，让其他线程有机会进入同步方法。
 */

public class Eleven {
    private int count;

    private synchronized void syn() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (count == 5) {
//                //此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后让循环继续
//                int i = 1 / 0;
//            }
            try {
                if (count == 5) {
                    int i = 1 / 0;
                }
            } catch (Exception e) {
                //捕获到异常，可以将事物回滚，然后重新抛出自定义异常，这样一来，事务回滚到线程执行前的状态，并且通过
                //抛出自定义异常，将锁释放掉了，其他线程有机会进入同步方法，并且状态也是正确的初始状态！
                e.printStackTrace();
                count = 0;
                throw new BusinessException("线程" + Thread.currentThread().getName() + "执行异常，恢复状态，抛出异常释放锁！");
            }
        }
    }

    public static void main(String[] args) {
        Eleven eleven = new Eleven();
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                eleven.syn();
//            }
//        });
        Thread thread = new Thread(() -> eleven.syn(), "thread 1");
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * 如果第一个线程在发生异常时不释放锁，那么下面这个线程根本就不会启动。
         */
        new Thread(eleven::syn, "thread 2").start();

    }
}
