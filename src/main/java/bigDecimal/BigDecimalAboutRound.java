package bigDecimal;

import java.math.BigDecimal;

/**
 * ROUND_CEILING(朝正无穷方向round 如果为正数，行为和round_up一样，如果为负数，行为和round_down一样 1.55 1.6 ； -1.55 -1.5)
 * 如果 BigDecimal 是正的，则做 ROUND_UP 操作；如果为负，则做 ROUND_DOWN 操作。
 * <p>
 * ROUND_FLOOR(朝负无穷方向round 如果为正数，行为和round_down一样，如果为负数，行为和round_up一样 1.55 1.5  ； -1.55 -1.6)
 * 如果 BigDecimal 为正，则作 ROUND_UP ；如果为负，则作 ROUND_DOWN 。
 * <p>
 * ROUND_UP
 * 总是在非 0 舍弃小数(即截断)之前增加数字。
 * <p>
 * ROUND_DOWN
 * 从不在舍弃(即截断)的小数之前增加数字。
 * <p>
 * ROUND_HALF_UP(四舍五入！！！)
 * 若舍弃部分>=.5，则作 ROUND_UP ；否则，作 ROUND_DOWN 。
 * <p>
 * ROUND_HALF_DOWN(五舍六入！！！)
 * 若舍弃部分> .5，则作 ROUND_UP；否则，作 ROUND_DOWN 。
 * <p>
 * ROUND_HALF_EVEN
 * 如果舍弃部分左边的数字为奇数，则作 ROUND_HALF_UP ；如果它为偶数，则作 ROUND_HALF_DOWN 。
 * <p>
 * ROUND_UNNECESSARY(必须所要求的的操作已经是精确的了，不能是1.567保留2位小数)
 * 该“伪舍入模式”实际是指明所要求的操作必须是精确的，，因此不需要舍入操作。
 * <p>
 * ————————————————
 * https://blog.csdn.net/qq_39164396/article/details/80992577
 * https://blog.csdn.net/hanlj123/article/details/80581576
 */


public class BigDecimalAboutRound {

    @org.junit.Test
    public void test1() {
        BigDecimal bigDecimal0 = new BigDecimal(2.20);
        BigDecimal bigDecimal1 = new BigDecimal(2.23);
        BigDecimal bigDecimal2 = new BigDecimal(2.25);
        BigDecimal bigDecimal3 = new BigDecimal(2.27);
        System.out.println("00000000000000000000000000000");
        System.out.println(bigDecimal0.setScale(1, BigDecimal.ROUND_UP));
        System.out.println(bigDecimal0.setScale(1, BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal0.setScale(1, BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal0.setScale(1, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(bigDecimal0.setScale(1, BigDecimal.ROUND_HALF_EVEN));
        System.out.println("11111111111111111111111111111");
        System.out.println(bigDecimal1.setScale(1, BigDecimal.ROUND_UP));
        System.out.println(bigDecimal1.setScale(1, BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal1.setScale(1, BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal1.setScale(1, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(bigDecimal1.setScale(1, BigDecimal.ROUND_HALF_EVEN));
        System.out.println("22222222222222222222222222222");
        System.out.println(bigDecimal2.setScale(1, BigDecimal.ROUND_UP));
        System.out.println(bigDecimal2.setScale(1, BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal2.setScale(1, BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal2.setScale(1, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(bigDecimal2.setScale(1, BigDecimal.ROUND_HALF_EVEN));
        System.out.println("33333333333333333333333333333");
        System.out.println(bigDecimal3.setScale(1, BigDecimal.ROUND_UP));
        System.out.println(bigDecimal3.setScale(1, BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal3.setScale(1, BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal3.setScale(1, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(bigDecimal3.setScale(1, BigDecimal.ROUND_HALF_EVEN));
    }

    /**
     * 如果我们按照上面的理解，下面得到的结果应该一个是6.429，一个是6.428

       但是实际的运行结果都是6.429 。这里要注意，这个怎么看呢，如要注意，这两个的本质都是四舍五入，
     如果你的结果总位数超过了你要保留的位数，都是按照四舍五入。

     那么什么时候才按照进位和舍位进行运算呢，只有你的结果的位数恰好比要保留的位数多一位，
     并且最后一位是恰好是5，才按照之前的规则进行运算。
     ---------------------
     */
    @org.junit.Test
    public void test2() {
        /**
         * 事实上，由于二进制无法精确地表示十进制小数6.428571428571429，但是编译器读到6.428571428571429之后，
         * 必须把它转成8个字节的double值，因 此，编译器只能用一个最接近的值来代替它了。
         */
        BigDecimal bigDecimal1 = new BigDecimal(6.428571428571429);
        System.out.println("bigDecimal1:" + bigDecimal1);
        System.out.println(bigDecimal1.setScale(3, BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal1.setScale(3, BigDecimal.ROUND_HALF_DOWN));
        System.out.println("****************************************************************");
        /**
         *如果你希望BigDecimal能够精确地表示你希望的数值，那么一定要使用字符串来表示小数，
         *并传递给BigDecimal的构造函数。
         *BigDecimal能够正确地把字符串转化成真正精确的浮点数。
         *
         * 只有你的结果的位数恰好比要保留的位数多一位，并且最后一位是恰好是5，才按照之前的规则进行运算。
         */
        BigDecimal bigDecimal2 = new BigDecimal("6.4285");
        System.out.println("bigDecimal2:" + bigDecimal2);
        System.out.println(bigDecimal2.setScale(3, BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal2.setScale(3, BigDecimal.ROUND_HALF_DOWN));
    }

    /**
     * 对于ROUND_HALF_DOWN模式，应该是“五舍六入”！
     * 1.55 -》 1.5
     * 1.56 -》 1.6
     * <p>
     * 但是下面发现两个BigDecimal的1.55和1.56，使用setScale方法指定ROUND_HALF_DOWN模式之后
     * 输出的结果对于被舍弃位是5没有按照预期舍去，而同样进位变成了1.6.
     * 但是如果使用两个BigDecimal的3.10和2.00调用除法函数，结果应该是1.55，对其使用ROUND_HALF_DOWN
     * 模式之后，输出结果按照预期舍去，变成了1.5！！！
     * 所以这里注意一下，使用ROUND_HALF_DOWN这个模式在调用BigDecimal加减乘除方法没问题，
     * 但是使用setScale方法指定ROUND_HALF_DOWN模式是有问题的！！！
     */
    @org.junit.Test
    public void test3() {
        BigDecimal bigDecimal = new BigDecimal(1.55);
        BigDecimal bigDecimal2 = new BigDecimal(1.56);

        System.out.println("1.55 setScale with ROUND_HALF_DOWN : " + bigDecimal.setScale(1, BigDecimal.ROUND_HALF_DOWN));
        System.out.println("1.56 setScale with ROUND_HALF_DOWN : " + bigDecimal2.setScale(1, BigDecimal.ROUND_HALF_DOWN));

        System.out.println("3.10 divide 2.00 with ROUND_HALF_DOWN : " + new BigDecimal("3.10").divide(new BigDecimal("2.00"), 1, BigDecimal.ROUND_HALF_DOWN));
    }

}