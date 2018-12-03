package spring.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class MyTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("当前时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}

public class MyTimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //延迟出发
        timer.schedule(new MyTask(), 1000);
        //间隔出发
        timer.schedule(new MyTask(),0,2000);
    }
}
