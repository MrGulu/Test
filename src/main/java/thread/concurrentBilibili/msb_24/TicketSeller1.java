package thread.concurrentBilibili.msb_24;

import java.util.LinkedList;
import java.util.List;

/**
 * N张火车票，每张票都有一个编号
 * <p>
 * 问题：
 * 1.可能卖重
 * 2.可能超量（最后一张票了，然后判断的都是size>0，然后remove，结果就是数组越界。）
 */
public class TicketSeller1 {

    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号： " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("sale--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
