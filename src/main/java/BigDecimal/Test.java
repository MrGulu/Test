package BigDecimal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class Test {
    public static void main(String[] args) {
        String intrate = "0.1234";
        String loanAmount = "138850";
        Double intrateDou = new Double(intrate) * 100;
        Double loanAmountDou = new Double(loanAmount);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String newIntrate = decimalFormat.format(intrateDou);
        String newLoanAmount = decimalFormat.format(loanAmountDou);
        System.out.println("newIntrate-->" + newIntrate);
        System.out.println("newLoanAmount-->" + newLoanAmount);

        BigDecimal fstPclTmpDecimal = new BigDecimal(.3);
        Double fstPclTmpDouble = new Double(String.valueOf(fstPclTmpDecimal)) * 100;
        String fstPcl = decimalFormat.format(fstPclTmpDouble);
        Map map = new HashMap();
        map.put("FstPcl", fstPcl + "%");
        System.out.println(map.get("FstPcl"));
    }

    @org.junit.Test
    public void test() {
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
     * 如果我们按照上面的理解，得到的结果应该一个是6.429，一个是6.428

       但是实际的运行结果都是6.429 。这里要注意，这个怎么看呢，如要注意，这两个的本质都是四舍五入，
     如果你的结果总位数超过了你要保留的位数，都是按照四舍五入。

       那么什么时候才按照进位和设为进行运算呢，只有你的结果的位数恰好比要保留的位数多一位，
     并且最后一位是恰好是5，才按照之前的规则进行运算。
     ---------------------
     */
    @org.junit.Test
    public void test1() {
        BigDecimal bigDecimal = new BigDecimal(6.428571428571429);
        System.out.println(bigDecimal.setScale(3,BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal.setScale(3,BigDecimal.ROUND_HALF_DOWN));
    }

    @org.junit.Test
    public void test2() {
        BigDecimal max = new BigDecimal(20000).multiply(new BigDecimal(0.95));
        System.out.println(max);
        BigDecimal max2 = new BigDecimal(20000).multiply(new BigDecimal(95)).divide(new BigDecimal(100));
        System.out.println(max2);
    }
}