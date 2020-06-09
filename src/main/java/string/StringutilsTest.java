package string;

import org.junit.Test;
import utils.StringUtils;

public class StringutilsTest {

    /**
     * @description 去除字符串空白字符，不限于空格，包括首尾、中间
     * https://www.cnblogs.com/gavincoder/p/9049766.html
     * 1、方法分类
     * str.trim(); //去掉首尾空格
     * str.replace(" ",""); //去除所有空格，包括首尾、中间
     * str.replaceAll(" ", ""); //去掉所有空格，包括首尾、中间
     * str.replaceAll(" +","");  //去掉所有空格，包括首尾、中间
     * str.replaceAll("\\s*", ""); //可以替换大部分空白字符， 不限于空格 ；
     * 　　　\\s* 可以匹配空格、制表符、换页符等空白字符的其中任意一个。
     * <p>
     * 2、replace和replaceAll是JAVA中常用的替换字符的方法,它们的区别是:
     * （1）　replace的参数是char和CharSequence,即可以支持字符的替换,也支持字符串的替换(CharSequence即字符串序列的意思,说白了也就是字符串);
     * （2）　replaceAll的参数是regex,即基于规则表达式的替换,比如,可以通过replaceAll("\\d", "*")把一个字符串所有的数字字符都换成星号;
     * 相同点：都是全部替换,即把源字符串中的某一字符或字符串全部换成指定的字符或字符串,如果只想替换第一次出现的,可以使用 。
     * replaceFirst(),这个方法也是基于规则表达式的替换,但与replaceAll()不同的时,只替换第一次出现的字符串;
     */
    @Test
    public void replaceSpace() {
        System.out.println("************************************************");
        String s = "   周杰 伦 ";
        String s1 = " 3701231995 1111 4311 ";
        String s2 = " 18 53543 43 21 ";
        System.out.println(StringUtils.replaceSpace(s));
        System.out.println(StringUtils.replaceSpace(s1));
        System.out.println(StringUtils.replaceSpace(s2));
        System.out.println("周杰伦".equals(StringUtils.replaceSpace(s)));
        System.out.println("370123199511114311".equals(StringUtils.replaceSpace(s1)));
        System.out.println("18535434321".equals(StringUtils.replaceSpace(s2)));
        System.out.println("************************************************");
    }

    @Test
    public void isChineseName() {
        System.out.println("************************************************");
        System.out.println(StringUtils.isChineseName("贺"));
        System.out.println(StringUtils.isChineseName("贺志"));
        System.out.println(StringUtils.isChineseName("贺·志刚"));
        System.out.println(StringUtils.isChineseName("贺·志·刚"));
        System.out.println(StringUtils.isChineseName("贺·志刚Q"));
        System.out.println(StringUtils.isChineseName("贺·志刚@"));
        System.out.println(StringUtils.isChineseName("贺·志刚#"));
        System.out.println("************************************************");
    }

    @Test
    public void isForigenName() {
        System.out.println("************************************************");
        System.out.println(StringUtils.isForigenName("Aaron"));
        System.out.println(StringUtils.isForigenName("Aaron Aaron  Aaron"));
        System.out.println(StringUtils.isForigenName("Aaron St. Aaron"));
        System.out.println(StringUtils.isForigenName("Aaron St. Aaron翔"));
        System.out.println(StringUtils.isForigenName("Aaron St. Aaron@"));
        System.out.println("************************************************");
    }

    @Test
    public void getRandomUUID() {
        String randomUUID = StringUtils.getRandomUUID();
        System.out.println(randomUUID);
    }

    @Test
    public void getNameUUIDFromBytes() {
        String s = "950111";
        for (int i = 0; i < 10; i++) {
            String nameUUIDFromBytes = StringUtils.getNameUUIDFromBytes(s.getBytes());
            System.out.println(nameUUIDFromBytes);
        }
    }
}
