package Set;

import org.junit.Test;
import utils.ReadProperties;

import java.util.*;

public class SetTest {
    @Test
    public void test1() {
        /**
         * 使用String的contains方法，底层使用的是indexOf方法判断字符串中是否包含
         */
        String outStsString = ReadProperties.ReadPropertiesFromfiles("/api.properties", "businessAuthenticationOutSts", "CONF_HOME");
        if (outStsString.contains("21")) {
            System.out.println("hava");
        }
        /**
         * String转成数组后，再转成List，然后使用HashSet的构造函数构造Set
         */
        String[] array = outStsString.split(",");
        List<String> list = Arrays.asList(array);
        System.out.println(list);
        Set<String> set1 = new HashSet(list);
        System.out.println(set1);
        if (set1.contains("21")) {
            System.out.println("have");
        }
        /**
         * String转成数组后，再转成List，然后使用Set的addAll方法
         */
        System.out.println("--------------------------------------");
        Set set2 = new HashSet();
        set2.addAll(list);
        if (set2.contains("21")) {
            System.out.println("have");
        }
        /**
         *String转成数组后，再转成List，然后使用Collections.addAll方法（传入List参数）
         */
        System.out.println("--------------------------------------");
        Set set3 = new HashSet();
        Collections.addAll(set3, list);
        if (set3.contains("21")) {
            System.out.println("have");
        }
        /**
         *String转成数组后，然后使用Collections.addAll方法（传入Array参数）
         */
        System.out.println("--------------------------------------");
        Set set4 = new HashSet();
        Collections.addAll(set4, array);
        if (set4.contains("21")) {
            System.out.println("have");
        }
    }
}
