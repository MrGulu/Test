package main.java.String;

import java.util.logging.Logger;

public class ParseIntAndValueOf {
    private static final Logger logger = Logger.getLogger("this.getClass()");
    public static void main(String[] args) {
        String s = "128";
        logger.info("init param:"+s);
        int i = Integer.parseInt(s);
        logger.info("converter param:"+i);
        Integer a = 3;
        int j = a.intValue();
        logger.info("intValue method:"+j);

        //当需要将int类型的数据转化为String类型时，String.valueOf方法和Integer.toString方法效果是一样的！
        String s1 = String.valueOf(a);
        logger.info("String.valueOf method:"+s1);
        String s2 = Integer.toString(a);
        logger.info("Integer.toString method:"+s2);
        Integer b = Integer.valueOf(s1);
        logger.info("Integer.valueOf method:"+b);
    }
}
