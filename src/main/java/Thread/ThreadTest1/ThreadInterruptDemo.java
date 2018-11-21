package Thread.ThreadTest1;

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("1.进入run()方法");
        try {
            Thread.sleep(10000);
            System.out.println("2.已经完成了休眠");
        } catch (InterruptedException e) {
            System.out.println("3.休眠被终止");
            return;
        }
        System.out.println("4.run()方法正常结束");
    }
}

public class ThreadInterruptDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread, "线程");
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("3.休眠被终止");
        }
        thread.interrupt();
    }
}
