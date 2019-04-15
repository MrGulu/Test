package Exception;

import custom.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 1.e.printStackTrace();方法会输出到标准错误流，控制台是红色的显示。
 * 2.在子方法中try catch块中抛出的异常，catch块中用Exception捕获，然后重新抛出这个异常，
 *  抛出的就是一开始的异常类型。
 * 3.在各种异常的构造函数中，如果传入了Throwable cause这个参数，那么在打印异常栈轨迹的时候
 *  就会带有Caused by的形式，表名这个异常是由另一个异常（Caused by的异常，也就是传入的Throwable cause这个参数的异常）造成的
 *  相当于new了一个异常对象之后，调用e.initCause(Throwable cause);方法！
 *  所以以后这种情况就直接在新抛出的异常中将捕获到的异常传入其中Throwable cause参数。
 */
public class Test {
    public static final Logger logger = LoggerFactory.getLogger(Test.class);
    class InnerTest {
        public String returnString(int i) throws CustomException {
            String s = null;
            if (i == 1) {
                s = "one";
            } else if (i == 2) {
                throw new CustomException(RspCustomEnum.ERR10001, "==>自定义异常哇！");
            } else if (i == 3) {
                try {
                    throw new CustomException(RspCustomEnum.ERR10001, "Rethrow异常哇！");
                } catch (Exception e) {
                    throw e;
                }
            } else if (i == 4) {
                try {
                    s.toString();
                } catch (Exception e) {
                    throw new BusinessException(e);
                }
            } else if (i == 5) {
                try {
                    throw new IOException("IO异常哇！");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return s;
        }
    }
    public static void main(String[] args) {
        InnerTest innerTest = new Test().new InnerTest();
        String out = null;
        try {
            out = innerTest.returnString(4);
        } catch (CustomException e) {
            System.out.println(e.getErrorCode()+":"+e.getMessage());

            System.out.println("*************ExceptionUtils.getStackTraceAsString(e);**************");
            System.out.println(ExceptionUtils.getStackTraceAsString(e));

            System.out.println("*************e.printStackTrace();**************");
            e.printStackTrace();

            System.out.println("*************logger.error(\"log\", e);**************");
            logger.error("log", e);

            System.out.println("*************logger.info(\"e.toString:{}\", e);**************");
            logger.info("e.toString:{}", e);

            System.out.println("*************e.initCause(new NullPointerException());后打印e.printStackTrace();**************");
            //e.initCause(new NullPointerException());方法会拼接其他的异常，
            // 这样在打印异常栈信息的时候，除了打印正常的异常栈轨迹外，还会以Caused by:的形式打印拼接的异常
            e.initCause(new NullPointerException());
            e.printStackTrace();

            System.out.println("*************e.fillInStackTrace();后打印e.printStackTrace();**************");
            //e.fillInStackTrace();方法会重新装配异常信息，异常栈信息会从此处作为起始点记录
            e.fillInStackTrace();
            e.printStackTrace();
        } catch (RuntimeException e) {
            Throwable throwable = e.getCause();
            if (throwable instanceof IOException) {
                logger.error("log", throwable);
            } else {
                logger.error("",e);
            }
        } catch (Exception e) {
            Throwable throwable = e.getCause();
            if (throwable instanceof CustomException) {
                logger.error("log",throwable);
            }
        }
        System.out.println(out);
    }

}
