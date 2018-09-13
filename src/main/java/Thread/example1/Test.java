package Thread.example1;

public class Test {
    @org.junit.Test
    public void test1() {
        SourceA s = new SourceA();
        TestThread tt = new TestThread(s);
        TestRunnable tr = new TestRunnable(s);
        Thread t = new Thread(tr);
        System.out.println("调用线程1");
        tt.start();
        System.out.println("调用线程2");
        t.start();
    }
}
