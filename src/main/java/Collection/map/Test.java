package Collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void test1() {
        Map<String,Object> map = new HashMap<>(4);
        map.put("key","null");
        String s = (String) map.get("test");
        String s1 = (String) map.get("key");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s==s1);
        //会报空指针
//        System.out.println(s.equals(s1));
    }

    /**
     *entrySet遍历
     */
    @org.junit.Test
    public void test2() {
        Map<String, String> map = new HashMap<>(8);
        map.put("key", "value");
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,String> entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:"+key+";value:"+value);
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:" + key + ";value:" + value);
        }


    }
}
