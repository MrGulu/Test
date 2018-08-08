package Exception;

public class ExceptionExample {
    public void functionA() throws MyException{
        functionB();
    }
    public void functionB() throws MyException{
//        String s = null;
//        System.out.println(s.length());//此处发生异常，不会继续往下执行，异常冒泡抛到了main，被catch捕获
        System.out.println("Oringinally create a MyException and throw it out!");
        throw new MyException("MyException");
    }
    public static void main(String[] args) {
        try {
            ExceptionExample example = new ExceptionExample();
            example.functionA();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}
