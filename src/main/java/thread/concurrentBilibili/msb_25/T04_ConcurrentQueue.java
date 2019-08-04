package thread.concurrentBilibili.msb_25;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 队列用的很多
 */
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        /*无界单向队列，什么时候内存耗完了才出错，如果内存没耗完，就会一直加*/
        Queue<String> strs = new ConcurrentLinkedQueue<>();
        /*类似于add，但是add在容量达到最大时会抛异常；但是
         * offer不会，返回boolean类型判断是否添加元素成功*/
        for (int i = 0; i < 10; i++) {
            strs.offer("a" + i);
        }

        System.out.println(strs);
        System.out.println(strs.size());

        /*从头上拿（先放入的元素），返回拿到的元素，拿完了后删掉拿的元素*/
        System.out.println(strs.poll());
        System.out.println(strs.size());
        /*从头上拿（先放入的元素），返回拿到的元素，用一下，但是不删除拿的元素*/
        System.out.println(strs.peek());
        System.out.println(strs.size());

        //双端队列Deque。可以从两段拿，两端加，具体看API文档
    }
}
