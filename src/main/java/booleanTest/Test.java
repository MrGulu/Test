package booleanTest;

import com.alibaba.fastjson.JSONObject;

public class Test {
    @org.junit.Test
    public void test1() {
        boolean b = true;
        b = Boolean.parseBoolean(null);
        System.out.println(b);
    }

    @org.junit.Test
    public void test2() {
        boolean b = false;
        String s = "true";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", s);
        b = jsonObject.getBoolean("key");
        System.out.println(b);
        System.out.println(jsonObject.getBoolean("key1"));
        //将null赋给Boolean类型会触发空指针异常！
        b = jsonObject.getBoolean("key1");
        System.out.println(b);
    }

    @org.junit.Test
    public void test3() {
        Boolean b = false;
        String s = "true";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", s);
        System.out.println(b);
        b = jsonObject.getBoolean("key");
        System.out.println(b);
        System.out.println(jsonObject.getBoolean("key1"));
        //将null赋给Boolean类型会触发空指针异常！
        b = jsonObject.getBoolean("key1");
        System.out.println(b);
    }

    @org.junit.Test
    public void test4() {
        Boolean b = false;
        String s = "true";
        b = Boolean.parseBoolean(s);
        System.out.println(b);
    }


}
