package Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionExample3 {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionExample3.class);
    public void functionA() {
        functionB();
    }
    public void functionB() {
        try {
//        String s = null;
//        System.out.println(s.length());//此处发生异常，不会继续往下执行，异常冒泡抛到了main，被catch捕获
            System.out.println("Oringinally create a MyException and throw it out!");
            throw new MyException("functionB函数异常！@！");
        } catch (Exception e) {
            logger.info("捕获到异常！");
            throw new MyException(e.getLocalizedMessage());
        }
    }
    public static void main(String[] args) {
        try {
            ExceptionExample3 example = new ExceptionExample3();
            example.functionA();
            logger.info("Main接收到异常不会执行！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }
}
