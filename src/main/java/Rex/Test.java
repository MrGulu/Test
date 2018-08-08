package Rex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String reg="(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
                "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        /*由于Pattern的构造函数是私有的,不可以直接创建,所以通过静态方法compile(String regex)方法来创建,
        将给定的正则表达式编译并赋予给Pattern类*/
        Pattern pattern = Pattern.compile(reg);
        /*返回正则表达式的字符串形式,其实就是返回Pattern.complile(String regex)的regex参数*/
        String patternStr = pattern.pattern();
        System.out.println(patternStr);
        //下面用Pattern.matcher()方法源码中实际就是通过下面第二种方式
        Boolean b = Pattern.matches(reg,"0538-82921678" );
        System.out.println(b);
        Matcher matcher = pattern.matcher("0538-82921678");
        Boolean b2 = matcher.matches();
        System.out.println(b2);
    }
}
