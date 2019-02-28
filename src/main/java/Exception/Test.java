package Exception;

public class Test {
    class InnerTest {
        public String returnString(int i) throws CustomException {
            String s = null;
            if (i == 1) {
                s = "one";
            } else if (i == 2) {
                throw new CustomException(RspCustomEnum.ERR10001, "==>自定义异常哇！");
            }
            return s;
        }
    }
    public static void main(String[] args) {
        InnerTest innerTest = new Test().new InnerTest();
        String out = innerTest.returnString(2);
        System.out.println(out);
    }

}
