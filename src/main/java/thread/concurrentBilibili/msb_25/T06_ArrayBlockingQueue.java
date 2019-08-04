package thread.concurrentBilibili.msb_25;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        //满了会阻塞，停在这不动了，无限制阻塞下去
//        strs.put("aaa");
        //会报异常
//        strs.add("aaa");
        //不会报异常，返回boolean是否加成功
//        strs.offer("aaa");
        //按时间阻塞，多长时间加不进去就不加了
        strs.offer("aaa", 1, TimeUnit.SECONDS);

        System.out.println(strs);
    }
}
