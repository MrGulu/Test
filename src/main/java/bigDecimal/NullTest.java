package bigDecimal;

import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;

public class NullTest {
    /**
     * 1.BigDecimal的构造器，如果传入的字符串是null，则会报空指针异常
     * 2.如果新new了一个对象，则该对象的所有成员都会是初始化之后的值、
     * 对于String和BigDecimal是null，对于八种基本数据类型都是各自类型的0
     */
    @Test
    public void test1() {
        BigDecimal bigDecimal = new BigDecimal("59670862");
        System.out.println(bigDecimal);
        ClassTest classTest = new ClassTest();
        System.out.println(classTest.toString());
    }

    @Data
    private class ClassTest {
        private byte aByte;
        private short aShort;
        private int anInt;
        private long aLong;
        private float aFloat;
        private double aDouble;
        private boolean aBoolean;
        private char aChar;
        private String string;
        private BigDecimal bigDecimal;
    }

}
