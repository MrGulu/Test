package BigDecimal;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class DecimalFormatTest {
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

    /**
     * 将小数装换成百分比输出
     * 将double类型保留小数点后两位，转换成
     */
    @Test
    @SuppressWarnings("all")
    public void test1() {
//      ================================================================================
        double f = 0.5585;
//        BigDecimal  b = new BigDecimal(f);
//        double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(f1);
        System.out.println(Integer.parseInt(new DecimalFormat("0").format(f * 100)) + "%");//百分比没有小数点
//      ===========================首选===================================================
        double result1 = 0.51111122111111;
        DecimalFormat df = new DecimalFormat("0.00%");
        String r = df.format(result1);
        System.out.println(r);//great
//      =================================================================================
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumIntegerDigits(3);
        num.setMaximumFractionDigits(2);
        double csdn = 0.55555555555555555;
        System.out.println(num.format(csdn));//good
//      =================================================================================
        double result = 1;
        int temp = (int) (result * 1000);
        result = (double) temp / 10;
        System.out.println(result + "%");//100%  变成了  100.0%
    }

    @Test
    @SuppressWarnings("all")
    public void test2() {
        double result1 = 0.06;
        DecimalFormat df = new DecimalFormat("0.00%");
        String r = df.format(result1);
        System.out.println(r);//great
    }
}
