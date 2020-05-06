package string;

import java.util.Date;
import java.util.Locale;

/**
 * 查看JDK文档得知,String.format方法的第一个参数是有个公式可以套的

 %[argument_index$][flags][width][.precision]conversion

 这里我们只要牢记这个公式就可以,下面说下每个颜色所代表的含义

 argument_index: 可选,是一个十进制整数，用于表明参数在参数列表中的位置。第一个参数由 "1$" 引用，第二个参数由 "2$" 引用，依此类推。

 flags: 可选,用来控制输出格式

 width: 可选,是一个正整数，表示输出的最小长度

 precision:可选,用来限定输出字符数

 conversion:必须,用来表示如何格式化参数的字符
 */
public class Format {
    public static void main(String[] args) {
        String s1 = String.format("hello %s","world");
        System.out.println(s1);
        String s2 = String.format("%2$s love %1$s", "xiaodugulu", "xiaogenban");
        System.out.println(s2);
        String s3 = String.format("%1$06d", 666);
        System.out.println(s3);
        String s4 = String.format("%1$,+8d", 666666);//用“，”隔开，输出最少8位，不够前用“+”补
        System.out.println(s4);

        //%n是换行符
        System.out.printf("字母a的大写是：%c %n", 'A');

        System.out.printf("3>7的结果是：%b %n", 3>7);

        System.out.printf("100的一半是：%d %n", 100/2);

        System.out.printf("100的16进制数是：%x %n", 100);

        System.out.printf("100的8进制数是：%o %n", 100);

        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);

        System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);

        System.out.printf("上面价格的指数表示：%e %n", 50*0.85);

        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);

        System.out.printf("上面的折扣是%d%% %n", 85);//输出：上面的折扣是85%

        System.out.printf("字母A的散列码是：%h %n", 'A');




        System.out.printf("显示正负数的符号：%+d与%d%n", 99,-99);

        System.out.printf("最牛的编号是：%03d%n", 7);

        System.out.printf("Tab键的效果是：% 8d%n", 7);

        System.out.printf("整数分组的效果是：%,d%n", 9989997);

        System.out.printf("一本书的价格是：%2.2f元%n", 49.8);//注意这个




        Date date= new Date();                                                                    // 创建日期对象

        System.out.printf("全部日期和时间信息：%tc%n",date);                // 格式化输出日期或时间

        System.out.printf("年-月-日格式：%tF%n",date);//常用

        System.out.printf("月/日/年格式：%tD%n",date);

        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);

        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);

        System.out.printf("HH:MM格式（24时制）：%tR%n",date);



        String str=String.format(Locale.US,"英文月份简称：%tb",date);      // 格式化日期字符串

        System.out.println(str);                                                                              // 输出字符串内容

        System.out.printf("本地月份简称：%tb%n",date);

        str=String.format(Locale.US,"英文月份全称：%tB",date);

        System.out.println(str);

        System.out.printf("本地月份全称：%tB%n",date);

        str=String.format(Locale.US,"英文星期的简称：%ta",date);

        System.out.println(str);

        System.out.printf("本地星期的简称：%tA%n",date);

        System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n",date);

        System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n",date);

        System.out.printf("一年中的天数（即年的第几天）：%tj%n",date);

        System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n",date);

        System.out.printf("两位数字的日（不足两位前面补0）：%td%n",date);

        System.out.printf("月份的日（前面不补0）：%te%n",date);




        System.out.printf("2位数字24时制的小时（不足2位前面补0）:%tH%n",date);

        System.out.printf("2位数字12时制的小时（不足2位前面补0）:%tI%n",date);

        System.out.printf("2位数字24时制的小时（前面不补0）:%tk%n",date);

        System.out.printf("2位数字12时制的小时（前面不补0）:%tl%n",date);

        System.out.printf("2位数字的分钟（不足2位前面补0）:%tM%n",date);

        System.out.printf("2位数字的秒（不足2位前面补0）:%tS%n",date);

        System.out.printf("3位数字的毫秒（不足3位前面补0）:%tL%n",date);

        System.out.printf("9位数字的毫秒数（不足9位前面补0）:%tN%n",date);

        String str1=String.format(Locale.US,"小写字母的上午或下午标记(英)：%tp",date);

        System.out.println(str1);                          // 输出字符串变量str的内容

        System.out.printf ("小写字母的上午或下午标记（中）：%tp%n",date);

        System.out.printf("相对于GMT的RFC822时区的偏移量:%tz%n",date);

        System.out.printf("时区缩写字符串:%tZ%n",date);

        System.out.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts%n",date);

        System.out.printf("1970-1-1 00:00:00 到现在所经过的毫秒数：%tQ%n",date);
    }
}
