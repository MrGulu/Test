package forloop;

public class Test {
    @org.junit.Test
    public void test1() {
        int i;
        for (i = 0; i < 5; i++) {
            if (i == 4) {
                break;
            }
        }
        System.out.println(i);
    }
}
