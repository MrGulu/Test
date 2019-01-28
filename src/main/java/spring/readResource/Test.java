package spring.readResource;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Scanner;

public class Test {
    /**
     * Delimiter英文意思为分隔符；useDelimiter( )方法默认以空格作为分隔符；当然也修改，如：
     useDelimiter(",");   //以','为分隔符
     useDelimiter("\n"); //“\n”换行符（回车）作为输入的分隔符。
     */
    /**
     * next方法其实也是可以获取字符串的。但是next不能获取空格，比如输入Hello World，使用next方法只会得到Hello
     而使用nextLine方法会得到完整的Hello World
     因为nextLine方法是以回车键为结束标识的。
     */
    /**
     * next()和nextLine()的区别
     next():只读取输入直到空格。它不能读两个由空格或符号隔开的单词。此外，next()在读取输入后将光标放在同一行中。(next()只读空格之前的数据,并且光标指向本行)
     nextLine():读取输入，包括单词之间的空格和除回车以外的所有符号(即。它读到行尾)。读取输入后，nextLine()将光标定位在下一行。
     */
    @org.junit.Test
    public void test1() throws IOException {
        String info = "www.madadai.com www.caxins.com";
        Resource resource = new ByteArrayResource(info.getBytes());
        if (resource.exists()) {
            Scanner scanner = new Scanner(resource.getInputStream());
            scanner.useDelimiter(" ");
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        }
    }

    @org.junit.Test
    public void test2() {

    }
}
