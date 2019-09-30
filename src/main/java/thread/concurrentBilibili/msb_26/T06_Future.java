package thread.concurrentBilibili.msb_26;

import java.util.concurrent.*;

public class T06_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //将Callable包装进FutureTask中
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });
        //new一个线程，将FutureTask作为参数传入（因为new Thread（）参数不支持Callable类型。）
        new Thread(task).start();

        //阻塞,什么时候完成，拿到值才不再阻塞
        System.out.println(task.get());

        /*********************************************************************/

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> future = service.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        System.out.println(future.get());
        /**
         * 下面调用isDone是false，因为主线程把另外一个线程启动之后，直接就isDone所以是false，
         * 如果先调用get然后idDone，那肯定是true
         */
        System.out.println(future.isDone());

        //如果不调用下面的shutdown，那么会看到这个程序一直是运行状态的，没有停止
        service.shutdown();
    }
}
