package FastJson.test2;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestGet {
    public static void main(String[] args) {
        //测试get方法
        String testString = "{\n" +
                "    \"code\": \"0\",\n" +
                "    \"message\": \"调用接口成功！\",\n" +
                "    \"data\": {\n" +
                "        \"loanMsg\": {\n" +
                "            \"models\": \"别克Electra-3.8-AT\",\n" +
                "            \"dealerName\": \"北京恒尚宇彤商贸有限公司\",\n" +
                "            \"address\": \"北京市北京市昌平区龙德广场\",\n" +
                "            \"intrate\": 0.125,\n" +
                "            \"aprvTerm\": \"12\",\n" +
                "            \"dealerAcctno\": \"11014518143007\",\n" +
                "            \"userName\": \"贱贱专用\",\n" +
                "            \"idNo\": \"110221198001010011\",\n" +
                "            \"lanchBank\": \"微众银行\",\n" +
                "            \"brand\": \"别克\",\n" +
                "            \"loanAmount\": 6283\n" +
                "        },\n" +
                "        \"repyMsg\": {\n" +
                "            \"repyDay\": \"22\",\n" +
                "            \"repyCardno\": \"1111111111111111\",\n" +
                "            \"phoneNum\": null\n" +
                "        },\n" +
                "        \"mrtgrMsg\": {\n" +
                "            \"mrtgrType\": \"01\",\n" +
                "            \"mrtgrName\": \"贱贱专用\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(testString);
        Object test = jsonObject.get("data");
        System.out.println(test);
        JSONObject test1 = jsonObject.getJSONObject("data");
        JSONObject loanMsg = test1.getJSONObject("loanMsg");
        String userName = test1.getJSONObject("loanMsg").getString("userName");
        System.out.println(test1);
        System.out.println(loanMsg);
        System.out.println(userName);
        Object msg = jsonObject.get("message");
        String msg1 = jsonObject.getString("message");
//        报错
//        JSONObject msg2 = jsonObject.getJSONObject("message");
        System.out.println(msg);
        System.out.println(msg1);
        Object code = jsonObject.get("code");
        String code1 = jsonObject.getString("code");
        Integer code2 = jsonObject.getInteger("code");
        int code3 = jsonObject.getIntValue("code");
        BigInteger code4 = jsonObject.getBigInteger("code");
        BigDecimal code5 = jsonObject.getBigDecimal("code");
        System.out.println(code);
        System.out.println(code1);
        System.out.println(code2);
        System.out.println(code3);
        System.out.println(code4);
        System.out.println(code5);

    }
}
