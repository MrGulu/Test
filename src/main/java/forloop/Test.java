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

    @org.junit.Test
    public void test2() {
        for (int w = 0; w < 5; w++) {
            int i = 2, j = 2, k = 1;
            if (i == 1) {
                if (j == 1) {
                    continue;
                }
                k = 2;
                System.out.println(k);
            } else {
                k = 3;
                System.out.println(k);
            }
        }
    }
}
