package regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    private static Pattern userNamePattern = Pattern
            .compile("^([a-zA-Z][a-z0-9A-Z]+)$");
    public static void main(String[] args) {
        String reg="(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
                "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        /*由于Pattern的构造函数是私有的,不可以直接创建,所以通过静态方法compile(String regex)方法来创建,
        将给定的正则表达式编译并赋予给Pattern类*/
        Pattern pattern = Pattern.compile(reg);
        /*返回正则表达式的字符串形式,其实就是返回Pattern.complile(String regex)的regex参数*/
        String patternStr = pattern.pattern();
        System.out.println(patternStr);
        /**
         * 下面用Pattern.matcher()方法源码中实际就是通过下面第二种方式
         *    第一种方式是直接调用Pattern.matches(String regex, CharSequence input)静态方法，
         * 这个方法其实走的也是第二种方法，不过调用简单，不需自己再调用compile生成Pattern对象和调用
         * matcher方法生成Matcher对象，然后再调用Matcher类的matches方法。
         */
        Boolean b = Pattern.matches(reg,"0538-82921678" );
        System.out.println(b);
        Matcher matcher = pattern.matcher("0538-82921678");
        Boolean b2 = matcher.matches();
        System.out.println(b2);
    }

    @org.junit.Test
    public void test1() {
        String usrCde = "usr2no54353fd765765js5435kf56435j5f7l55654ds6757jfl8768kds";
        if (userNamePattern.matcher(usrCde).matches()) {
            System.out.println("匹配通过！");
        } else {
            System.out.println("匹配不通过！");
        }
    }

    @org.junit.Test
    public void test2() {
        //注意\d前面还有一个\，是转义字符
        String mobileRegex = "^1[34578]\\d{9}$";
        String mobileRegex11 = "^\\d\\d{10}$";
        String mobileRegex15 = "^\\d\\d{10,14}$";
        String testMobileTrue = "18562310571";
        String testMobileFalse = "12562310571";
        String testMobile = "23562310571";
        String testMobile15True = "053282343212";
        String testMobile15False = "0532823432121234";
        Pattern mobilePattern = Pattern.compile(mobileRegex);
        Matcher mobileMatcher = mobilePattern.matcher(testMobileTrue);
        //1.第一种按部就班方式-麻烦
        boolean flag1 = mobileMatcher.matches();
        if (flag1) {
            System.out.println("通过~");
        } else {
            System.out.println("未通过~");
        }
        //2.简单方式
        boolean flag2 = Pattern.matches(mobileRegex, testMobileFalse);
        if (flag2) {
            System.out.println("通过~");
        } else {
            System.out.println("未通过~");
        }
        //3.直接使用字符串的matches方法，调用的是上面的简单方式
        boolean flag3 = testMobileTrue.matches(mobileRegex11);
        if (flag3) {
            System.out.println("通过~");
        } else {
            System.out.println("未通过~");
        }
    }

    /**
     * 使用正则表达式切割字符串
     */
    @org.junit.Test
    public void splitDemo() {
        String str = "a b  c   d";
        //空格一个或多个
        String reg = " +";
        String[] array = str.split(reg);
        for (String s : array) {
            System.out.println(s);
        }
        System.out.println("******************toString of array*********************");
        System.out.println(Arrays.toString(array));
    }

    /**
     * 使用正则表达式切割字符串。==》组
     */
    @org.junit.Test
    public void splitDemoWithGroup() {
        String str = "erkktyqqquizzzzzo";
        //按照叠词来进行切割
        String reg = "(.)\\1+";
        //可以将规则封装成一个组。用()完成。组的出现都有编号。
        //从1开始。 想要使用已有的组可以通过  \n(n就是组的编号)的形式来获取。======重点
        String[] arr = str.split(reg);
        System.out.println(arr.length);
        for (String s : arr) {
            System.out.println(s);
        }
    }

    /**
     * 使用正则替换
     */
    @org.junit.Test
    public void replaceAllDemo() {
        //将字符串中的数字替换成#。
        String str = "wer1389980000ty1234564uiod234345675f";
        String reg = "\\d{5,}";
        str = str.replaceAll(reg, "#");
        System.out.println(str);
    }

    /**
     * 使用正则替换==》组
     */
    @org.junit.Test
    public void replaceAllDemoWithGroup() {
        //将字符串中的数字替换成#。
        String str = "wer1389980000ty1234564uiod234345675f";
        String reg = "(.)\\1+";
        str = str.replaceAll(reg, "#");
        System.out.println(str);
    }

    /**
     * 将字符串中的符合规则的子串取出。
     */
    @SuppressWarnings("all")
    @org.junit.Test
    public void getDemo() {
        String str = "yin yu shi wo zui cai de yu yan";
        System.out.println(str);
        //匹配只有三个字母的单词
        String reg = "\\b[a-z]{3}\\b";
        //将规则封装成对象。
        Pattern p = Pattern.compile(reg);
        //让正则对象和要作用的字符串相关联。获取匹配器对象。
        Matcher m = p.matcher(str);
        //start() 字符的开始下标（包含）
        //end()   字符的结束下标（不包含）
        while (m.find()) {
            System.out.println(m.group());
            System.out.println(m.start() + "...." + m.end());
        }

        //其实String类中的matches方法。用的就是Pattern和Matcher对象来完成的。
        //System.out.println(m.matches());
        //只不过被String的方法封装后，用起来较为简单。但是功能却单一。

        //将规则作用到字符串上，并进行符合规则的子串查找。
        // boolean b = m.find();
        // System.out.println(b);
        //用于获取匹配后结果。==========重要
        // System.out.println(m.group());
    }
}
