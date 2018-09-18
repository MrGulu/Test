package BigDecimal;

import java.text.DecimalFormat;

public class Test {
    public static void main(String[] args) {
        String intrate = "0.1234";
        String loanAmount = "138850";
        Double intrateDou = new Double(intrate)*100;
        Double loanAmountDou = new Double(loanAmount);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String newIntrate = decimalFormat.format(intrateDou);
        String newLoanAmount = decimalFormat.format(loanAmountDou);
        System.out.println("newIntrate-->"+newIntrate);
        System.out.println("newLoanAmount-->"+newLoanAmount);
    }
}
