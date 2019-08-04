package thread.concurrentBilibili.msb_26;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池的概念
 */
public class T07_ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> results = getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //cpu几核一般就为几
        final int cpuCoreNum = 4;

        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
        /**
         * 为什么不平均分？ 1-50000 50001-100000 ……
         *
         * 越大的数，计算是否是素数的时间越长，所以不均分计算，否则会让不同的核任务轻重不同
         */
        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 120000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);

        service.shutdown();
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override

        public List<Integer> call() throws Exception {
            return getPrime(startPos, endPos);
        }
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (isPrime(i)) {
                results.add(i);
            }
        }
        return results;
    }

    /**
     * 判断一个数是否是质数
     *
     * @param num
     * @return
     */
    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
