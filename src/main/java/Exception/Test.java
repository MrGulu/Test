package Exception;

public class Test {
    class InnerTest {
        public String returnString(int i) throws MyException{
            String s = null;
            if (i == 1) {
                s = "one";
            } else if (i == 2) {
                throw new MyException("exception");
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
