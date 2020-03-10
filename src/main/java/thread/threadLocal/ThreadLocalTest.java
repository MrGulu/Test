package thread.threadLocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reflect.Appl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadLocalTest {
    /*一般是static final修饰*/
    private static final ThreadLocal<Appl> APPL_THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * @description
     * 下面是测试ThreadLocal是线程本地变量。一个线程设置了对象，另一个线程获取不到，只能是
     * 线程自己设置，自己获取！
     * 下面还有一点就是两个线程塞进的对象都是同一个！这样的话，另一个线程拿出这个对象，做修改之后，
     * 另一个线程再get的时候拿到的是修改之后的对象！！！这样就达不到ThreadLocal的使用效果了！
     * 所以在使用过程中，不同线程ThreadLocal调用set方法的时候，放入的是不同的对象，不能共享对象！
     * 此为错误示范！！！
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Appl appl = new Appl();
        appl.setApplSeq(new BigDecimal("12313"));
        appl.setApplCde("33");

        Thread t1 = new Thread(() -> {
            APPL_THREAD_LOCAL.set(appl);
            System.out.println("t1 "+com.alibaba.fastjson.JSON.toJSONString(APPL_THREAD_LOCAL.get()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 "+com.alibaba.fastjson.JSON.toJSONString(APPL_THREAD_LOCAL.get()));

            latch.countDown();
        },"t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println("t2 "+com.alibaba.fastjson.JSON.toJSONString(APPL_THREAD_LOCAL.get()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            appl.setTypVer(2);
            APPL_THREAD_LOCAL.set(appl);
            System.out.println("t2 "+com.alibaba.fastjson.JSON.toJSONString(APPL_THREAD_LOCAL.get()));

            latch.countDown();
        },"t2");
        t2.start();

//        t1.join();
//        t2.join();

        //如果使用CountDownLatch，在最后就要使用await方法。否则就用上面join方法。
        latch.await();
        /*使用完记得remove，防止内存泄漏？*/
        APPL_THREAD_LOCAL.remove();
    }

    /**
     * @description
     * 下面是测试ThreadLocal用于实现多线程下不安全的SimpleDateFormat的线程安全实现。
     * 每个线程TL中塞入一个SimpleDateFormat，一开始使用的时候先get一下判空，如果为空，
     * 放入值，否则的话直接get出来用于格式化日期Date！！！
     *
     * 由于实际项目中线程很多都是复用的，所以这种情况下，使用该方法无论是从效率，内存占用，
     * 线程安全，"高大上"等方面考虑，都是不错的。
     *
     * 当然，多线程下不安全的SimpleDateFormat在使用的时候，也可以每次使用都new一个出来，
     * 这样也是线程安全的。
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            formatMethod();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            formatMethod();
            latch.countDown();
        },"t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            formatMethod();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            formatMethod();
            latch.countDown();
        },"t2");
        t2.start();

        latch.await();

        formatMethod();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatMethod();
        latch.countDown();
    }

    private static void formatMethod() {
        if (Objects.isNull(SIMPLE_DATE_FORMAT_THREAD_LOCAL.get())) {
            log.info("线程[{}]获取TL为null", Thread.currentThread());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SIMPLE_DATE_FORMAT_THREAD_LOCAL.set(format);
        }
        SimpleDateFormat format = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get();
        String timeString = format.format(new Date());
        log.info("线程[{}]format:[{}]", Thread.currentThread(), timeString);
    }
}
