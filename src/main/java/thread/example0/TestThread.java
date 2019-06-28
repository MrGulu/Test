package thread.example0;

public class TestThread extends Thread {
    private int time = 1;
    private SourceA s = null;
    String id = "002";

    public void setTime(int time) {
        this.time = time;
    }

    public TestThread(SourceA s){
        this.s = s ;
    }

    @Override
    public void run() {
        try {
            System.out.println("i will sleep"+ time);
            sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        synchronized(s){
            try {
                System.out.println("我"+ id +"要进行等待了");
                s.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("我被唤醒了");
            System.out.println("我存入了id"+id);
            s.setSource(id);
        }
    }

}
