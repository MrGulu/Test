package charset;

import java.nio.charset.StandardCharsets;

/**
 * @author tangwenlong
 * @description java中关于编码的测试
 */
public class CharsetTest {

    public static void main(String[] args) {
        String s = "param=你好";
        String s1, s2;

        s1 = new String(s.getBytes(), StandardCharsets.ISO_8859_1);
        System.out.println(s1);

        s2 = new String(s1.getBytes(StandardCharsets.ISO_8859_1));
        System.out.println(s2);

        System.out.println("********************************************************************");

        s1 = new String(s.getBytes(), StandardCharsets.UTF_8);
        System.out.println(s1);

        //以ISO_8859_1编码方式“解码”  以UTF-8编码方式”编码“获取到的字节数组
        s1 = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        System.out.println(s1);

        s2 = new String(s1.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println(s2);

        System.out.println("********************************************************************");

        s1 = new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println(s1);

        s2 = new String(s1.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        System.out.println(s2);

        System.out.println("********************************************************************");

        s1 = new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println(s1);

        s2 = new String(s1.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        System.out.println(s2);
    }
}
