package string;

import org.junit.Test;

public class EqualsTest {
    @Test
    public void test1() {
        String s = "1";
        String s1 = "1";
        int i = 1;
        Integer i1 = 1;
        if ("1".equals(s)) {
            System.out.println("*true*");
        }
        if (s1.equals(s)) {
            System.out.println("*true*");
        }
        if (i1.equals(1)) {
            System.out.println("*true*");
        }
        if (i1.equals(i)) {
            System.out.println("*true*");
        }
    }
}
