package jvm;

import org.junit.Test;

public class OtherTest {

    //https://mp.weixin.qq.com/s/ni4jaMgmoVPUWG7oRN0tGw
    @Test
    public void test1() {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            j = j++;
        }
        System.out.println(j);
    }

    @Test
    public void test2() {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            j++;
        }
        System.out.println(j);
    }

}
