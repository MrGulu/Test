package Thread.threadPractice;

/**
 * 关于对synchronized关键字的理解：
 * 1.在下面的例子当中，使用byte数组的lock对象作为锁，在多线程执行过程中，只有当两个线程中的一个获得这把锁，
 *  才可以执行同步代码块中的内容。
 * 2.在某个线程执行完一次for循环，也就是执行完了同步代码块中的内容，就释放了这把锁，其他线程此时就会争夺这把锁，
 *  谁争到谁就可以执行；在第一个线程执行完一次for循环，让出锁，然后被其他线程获得锁并执行同步代码快中的内容，此时
 *  第一个线程执行第二次for循环的时候就会阻塞（线程阻塞在锁池等待队列中），因为此时其他线程独占该锁，直到其他线程
 *  释放锁，才有可能被CPU选中而获得锁。
 * 3.在下面的例子当中，使用传过来的String username当作锁也是可以的。
 *
 */
public class Test {
    static class HasSelfPrivateNum {

        private int num = 0;

        byte[] lock = new byte[0];

        public void addI(String username) {
//            synchronized (username) {
//                System.out.println(username);
//            }
            synchronized (lock) {
                try {
                    System.out.println("线程名称为：" + Thread.currentThread().getName()
                            + "在" + System.currentTimeMillis() + "进入方法");
                    Thread.sleep(2000);
                    System.out.println("线程名称为：" + Thread.currentThread().getName()
                            + "在" + System.currentTimeMillis() + "离开方法");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    static class ThreadOne implements Runnable {

        private HasSelfPrivateNum hasSelfPrivateNum;

        ThreadOne(HasSelfPrivateNum hasSelfPrivateNum) {
            this.hasSelfPrivateNum = hasSelfPrivateNum;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadOne run()!");
                hasSelfPrivateNum.addI("one");
            }
        }
    }

    static class ThreadTwo implements Runnable {

        private HasSelfPrivateNum hasSelfPrivateNum;

        ThreadTwo(HasSelfPrivateNum hasSelfPrivateNum) {
            this.hasSelfPrivateNum = hasSelfPrivateNum;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadTwo run()!");
                hasSelfPrivateNum.addI("two");
            }
        }
    }

    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();

        ThreadOne threadOne = new ThreadOne(hasSelfPrivateNum);
        Thread one = new Thread(threadOne,"one");
        one.start();

        ThreadTwo threadTwo = new ThreadTwo(hasSelfPrivateNum);
        Thread two = new Thread(threadTwo,"two");
        two.start();
    }
}
