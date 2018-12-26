package Map;

import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void test1() {
        Map map = new HashMap();
        map.put("key","null");
        String s = (String) map.get("test");
        String s1 = (String) map.get("key");
        System.out.println(s);
        System.out.println(s1);
    }
}
