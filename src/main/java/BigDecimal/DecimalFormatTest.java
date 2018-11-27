package BigDecimal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
}
