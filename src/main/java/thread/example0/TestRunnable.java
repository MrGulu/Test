package thread.example0;

public class TestRunnable implements Runnable {

    private int time=1;
    private SourceA s;
    private String id = "001";
    public TestRunnable(SourceA s){
        this.s = s;
    }
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        try {
            System.out.println("i will sleep"+ time);
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        synchronized(s){
            s.notify();
            System.out.println("我唤醒了002！");
            System.out.println("我存入了id"+id);
            s.setSource(id);
        }
    }

}
