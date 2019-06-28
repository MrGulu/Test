package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionExample2 {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionExample2.class);
    public void functionA() {
        functionB();
    }
    public void functionB(){
        try {
//        String s = null;
//        System.out.println(s.length());//此处发生异常，不会继续往下执行，异常冒泡抛到了main，被catch捕获
            System.out.println("Oringinally create a CustomException and throw it out!");
            throw new CustomException("CustomException");
        } catch (Exception e) {
            logger.info("捕获到异常！",e);
        }
    }
    public static void main(String[] args) {
        try {
            ExceptionExample2 example = new ExceptionExample2();
            example.functionA();
            logger.info("Main接收到异常不会执行！");
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}
