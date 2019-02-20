package other;

import org.junit.Test;

public class OdevityTest {

    @Test
    public void test1() {
        int i = 6;
        System.out.println("取模判断奇偶性******");
        if ((i % 2) == 1) {
            System.out.println(i+"是奇数");
        } else {
            System.out.println(i+"是偶数");
        }
        System.out.println("位运算判断奇偶性******");
        if ((i & 1) == 1) {
            System.out.println(i+"是奇数");
        } else {
            System.out.println(i+"是偶数");
        }
    }
}
