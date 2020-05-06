package string;

import org.junit.Test;

import java.util.Arrays;

public class SplitTest {
    public static void main(String[] args) {
        String s = "I Love You!";
        String[] array = s.split(" ");
        for (String a :
                array) {
            prtString(a);
        }
    }

    static void prtString(String s) {
        System.out.println(s);
    }

    @Test
    public void test1() {
        String s = "43243242,";
        String[] array = s.split(",");
        System.out.println(Arrays.toString(array));
    }
}
