package forloopAndswitchTest;

import org.junit.Test;

/**
 * switch首先会将要比较的变量与case中的每个变量匹配，当匹配成功时（String.equals(Object) = true），就跳转到该case的位置。
 * 1.如果此处有break，程序将跳出switch语句块；
 * 2.如果此处无break，那么匹配完这个case之后，不会跳出switch语句块，并且在这个语句块中还没有结束，
 * 会继续顺序往下走，不过此时就没有String.equals(Object)的判断了，只有碰到break的时候才会结束！
 */
@SuppressWarnings("all")
public class SwitchTest {

    @Test
    public void test1() {
        String a = "a";
        switch (a) {
            case "a":
                System.out.println("aaa");
                break;
            case "b":
                System.out.println("bbb");
                break;
            case "c":
                System.out.println("ccc");
                break;
            default:
                System.out.println("default");
        }
    }

    @Test
    public void test2() {
        String a = "a";
        switch (a) {
            case "a":
                System.out.println("aaa");
            case "b":
                System.out.println("bbb");
                break;
            case "c":
                System.out.println("ccc");
            default:
                System.out.println("default");
        }
    }

    @Test
    public void test3() {
        String a = "b";
        switch (a) {
            case "a":
                System.out.println("aaa");
                break;
            case "b":
                System.out.println("bbb");
            case "c":
                System.out.println("ccc");
            default:
                System.out.println("default");
        }
    }
}
