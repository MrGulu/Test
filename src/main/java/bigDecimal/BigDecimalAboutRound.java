package bigDecimal;

import java.math.BigDecimal;



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

}