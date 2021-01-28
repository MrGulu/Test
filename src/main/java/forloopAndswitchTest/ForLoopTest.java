package forloopAndswitchTest;

public class ForLoopTest {

    /*break与forloop测试*/

    /**
     * 单层for循环，break直接跳出for循环
     */
    @org.junit.Test
    public void test1() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            if (i == 3) {
                break;
            }
        }
    }

    /**
     * 多层for循环，break跳出距离最近的一层for循环
     */
    @org.junit.Test
    public void test2() {
        for (int i = 0; i < 5; i++) {
            System.out.println("i=" + i);
            for (int j = 0; j < 5; j++) {
                System.out.println("j="+j);
                if (j == 3) {
                    break;
                }
            }
        }
    }

    /**
     * 多层for循环，break通过定义label直接跳出多层for循环，继续执行多层循环体后的代码
     */
    @org.junit.Test
    public void test3() {
        System.out.println("loop start");
        flag:
        for (int i = 0; i < 5; i++) {
            System.out.println("i=" + i);
            for (int j = 0; j < 5; j++) {
                System.out.println("j="+j);
                if (j == 3) {
                    break flag;
                }
            }
        }
        System.out.println("loop end");
    }


    /*continue与forloop测试*/


    /**
     * 单层for循环，continue结束当前循环的本次循环，继续下一次循环
     */
    @org.junit.Test
    public void test12() {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                continue;
            }
            System.out.println(i);
        }
    }

    /**
     * 多层for循环，continue结束本层循环的本次循环，继续本层循环的下一次循环
     */
    @org.junit.Test
    public void test22() {
        for (int i = 0; i < 5; i++) {
            System.out.println("i=" + i);
            for (int j = 0; j < 5; j++) {
                if (j == 3) {
                    continue;
                }
                System.out.println("j="+j);
            }
        }
    }



}
