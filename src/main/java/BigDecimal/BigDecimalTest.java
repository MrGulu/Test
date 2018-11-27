package BigDecimal;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class BigDecimalTest {
    /**
     * 一、 BigDecimal的计算
     */
    @Test
    public void test1() {
        double d = 9.84;
        double d2 = 1.22;
        //注意需要使用BigDecimal(String val)构造方法!!!
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(d2));
        System.out.println("bigDecimal:   " + bigDecimal);
        System.out.println("bigDecimal2:  " + bigDecimal2);
        //加法
        BigDecimal bigDecimalAdd = bigDecimal.add(bigDecimal2);
        double add = bigDecimalAdd.doubleValue();
        System.out.println("add:          " + add);

        //减法
        BigDecimal bigDecimalSubtract = bigDecimal.subtract(bigDecimal2);
        double subtract = bigDecimalSubtract.doubleValue();
        System.out.println("substract:    " + subtract);

        //乘法
        BigDecimal bigDecimalMultiply = bigDecimal.multiply(bigDecimal2);
        double multiply = bigDecimalMultiply.doubleValue();
        System.out.println("multiply:     " + multiply);

        //除法
        int scale = 2;//保留2位小数
        BigDecimal bigDecimalDivide = bigDecimal.divide(bigDecimal2, scale, BigDecimal.ROUND_HALF_UP);
        double divide = bigDecimalDivide.doubleValue();
        System.out.println("divide:       " + divide);

        System.out.println("**************************************************************");
        System.out.println("**************************************************************");

        //格式化
        double format = 12343171.6;

        //获取常规数值格式
        NumberFormat number = NumberFormat.getNumberInstance();
        String str = number.format(format);//12,343,171.6
        System.out.println("获取常规数值格式getNumberInstance:      " + str);

        //获取整数数值格式
        NumberFormat integer = NumberFormat.getIntegerInstance();
        str = integer.format(format);//如果带小数会四舍五入到整数12,343,172
        System.out.println("获取整数数值格式getIntegerInstance:     " + str);

        //获取货币数值格式
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        currency.setMinimumFractionDigits(2);//设置数的小数部分所允许的最小位数(如果不足后面补0)
        currency.setMaximumFractionDigits(4);//设置数的小数部分所允许的最大位数(如果超过会四舍五入)
        str = currency.format(format);//￥12,343,171.60
        System.out.println("获取货币数值格式getCurrencyInstance:    " + str);

        //获取显示百分比的格式
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMinimumFractionDigits(2);//设置数的小数部分所允许的最小位数(如果不足后面补0)
        percent.setMaximumFractionDigits(3);//设置数的小数部分所允许的最大位数(如果超过会四舍五入)
        str = percent.format(format);//1,234,317,160.00%
        System.out.println("获取显示百分比的格式getPercentInstance:  " + str);
    }

    /**
     * 二、典型的Double类型的数值运算
     * 见DoubleUtil
     */

    /**
     * 三、下面提一下两个精度问题：
     */

    /**
     * 问题一：BigDecimal的精度问题（StackOverflow上有个家伙问了相关的问题）
     * System.out.println(new BigDecimal(0.1).toString()); // 0.1000000000000000055511151231257827021181583404541015625
     * System.out.println(new BigDecimal("0.1").toString()); // 0.1
     * System.out.println(new BigDecimal(Double.toString(0.1000000000000000055511151231257827021181583404541015625)).toString());// 0.1
     * System.out.println(new BigDecimal(Double.toString(0.1)).toString()); // 0.1
     * <p>
     * <p>
     * 分析一下上面代码的问题（注释的内容表示此语句的输出）
     * <p>
     * 第一行：事实上，由于二进制无法精确地表示十进制小数0.1，但是编译器读到字符串"0.1"之后，必须把它转成8个字节的double值，
     * 因 此，编译器只能用一个最接近的值来代替0.1了，即 0.1000000000000000055511151231257827021181583404541015625。
     * 因此，在运行时，传给 BigDecimal构造函数的真正的数值是 0.1000000000000000055511151231257827021181583404541015625。
     * 第二行：BigDecimal能够正确地把字符串转化成真正精确的浮点数。
     * 第三行：问题在于Double.toString会使用一定的精度来四舍五入double，然后再输出。会。
     * Double.toString(0.1000000000000000055511151231257827021181583404541015625) 输出的事实上是"0.1"，
     * 因此生成的BigDecimal表示的数也是0.1。
     * 第四行：基于前面的分析，事实上这一行代码等价于第三行
     * <p>
     * 结论：
     * 1.如果你希望BigDecimal能够精确地表示你希望的数值，那么一定要使用字符串来表示小数，并传递给BigDecimal的构造函数。
     * 2.如果你使用Double.toString来把double转化字符串，然后调用BigDecimal(String)，这个也是不靠谱的，它不一定按你的想法工作。
     * 3.如果你不是很在乎是否完全精确地表示，并且使用了BigDecimal(double)，那么要注意double本身的特例，double 的规范本身定义了
     * 几个特殊的double值(Infinite，-Infinite，NaN)，不要把这些值传给BigDecimal，否则会抛出异常。
     * <p>
     * 问题二：把double强制转化成int，难道不是扔掉小数部分吗？
     * int x=(int)1023.99999999999999; // x=1024为什么？
     * 原因还是在于二进制无法精确地表示某些十进制小数，因此1023.99999999999999在编译之后的double值变成了1024。
     * <p>
     * 所以，把double强制转化成int确实是扔掉小数部分，但是你写在代码中的值，并不一定是编译器生成的真正的double值。
     * 验证代码：
     * double d = 1023.99999999999999;
     * int x = (int) d;
     * System.out.println(new BigDecimal(d).toString()); // 1024
     * System.out.println(Long.toHexString(Double.doubleToRawLongBits(d))); // 4090000000000000
     * System.out.println(x); // 1024
     * 前面提过BigDecimal可以精确地把double表示出来还记得吧。
     * <p>
     * 我们也可以直接打印出d的二进制形式，根据IEEE 754的规定，我们可以算出0x4090000000000000=(1024)。
     */
    @org.junit.Test
    public void test2() {
        //用int类型构造
        BigDecimal val1 = new BigDecimal(20000);
        //用double类型构造
        BigDecimal val2 = new BigDecimal(0.95);
        //如果希望BigDecimal能够精确地表示你希望的数值，那么一定要使用字符串来表示小数，
        //并传递给BigDecimal的构造函数。
        BigDecimal val3 = new BigDecimal("0.95");
        BigDecimal val4 = new BigDecimal(95);
        BigDecimal val5 = new BigDecimal(100);
        BigDecimal bigValue = val1.multiply(val2);
        BigDecimal bigValue1 = BigDecimal.valueOf(val1.multiply(val2).intValue());
        BigDecimal bigValue2 = BigDecimal.valueOf(val1.multiply(val2).doubleValue());
        BigDecimal bigValue3 = val1.multiply(val3);
        BigDecimal bigValue4 = val1.multiply(val3).setScale(0);
        BigDecimal bigValue5 = val1.multiply(val4).divide(val5);
        System.out.println("通过double构造的BigDecimal值：" + val2);
        System.out.println("通过double构造后multiply的值：" + bigValue);
        System.out.println("intValue直接丢掉小数部分:     " + bigValue1);
        System.out.println("doubleValue:               " + bigValue2);
        System.out.println("通过字符串小数：              " + bigValue3);
        System.out.println("通过字符串小数且指定精度：      " + bigValue4);
        System.out.println("都通过int构造无小数先乘后除：   " + bigValue5);
    }
}
