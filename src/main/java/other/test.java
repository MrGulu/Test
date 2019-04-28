package other;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ReadProperties;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static final Logger logger = LoggerFactory.getLogger(test.class);
    public static void main(String[] args) {
//        String s = "admin";
//        System.out.println(Long.valueOf(s));
        Long l = 0L;
        System.out.println(l.equals(0L));
        logger.info("hello");

        try {
            String docCde = URLDecoder.decode( "/%E8%BA%AB%E4%BB%BD%E8%AF%81%E6%98%8E%E6%9D%90%E6%96%99/%E8%BA%AB%E4%BB%BD%E8%AF%81/%E8%BA%AB%E4%BB%BD%E8%AF%81%E6%AD%A3%E9%9D%A2", "UTF-8");
            System.out.println(docCde);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    /**
     * 测试关于Map的相关用法
     */
    @Test
    public void test1() {
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> mapNull = null;
        Map<String,Object> mapTwo = new HashMap<>();;
        mapTwo.put("a", "A");
        mapTwo.put("b", "B");
        map.put("a", "A");
        map.put("b", "B");
        System.out.println(map.get("data"));
        map.put("data", mapNull);
        System.out.println(map.get("data"));
        System.out.println(map);

        //map.get方法在取得null时，进行强转，并不会报异常
        Map<String, Object> mapGet = (Map<String, Object>) map.get("data");
        System.out.println(mapGet);

        String getA = (String) map.get("a");
        //map.get方法取得Object对象，如果是字符串的实例，那么可以用equals直接比较
        System.out.println("A".equals(map.get("a")));

        map.put("mapTwo", mapTwo);
        mapNull = (Map<String, Object>) map.get("mapTwo");
        System.out.println(mapNull);

        String applSeq = "1";
        String companyPro = "1";
        String companyCity = "1";
        String companyArea = "1";
        String companyAddr = "1";
        String companyTel = "1";
        logger.info("业务编号{}:公户省市区编码:{},{},{};详细地址:{};联系电话:{}",
                new Object[]{applSeq.toString(), companyPro, companyCity, companyArea, companyAddr, companyTel});
    }

    @Test
    public void test2() {
        BigDecimal bigDecimal = null;
        String str = bigDecimal.toString();
        System.out.println(str);
    }


    @Test
    @SuppressWarnings("all")
    public void readProperties() throws UnsupportedEncodingException {
        List list = new ArrayList();
        list.add(ReadProperties.ReadPropertiesFromfiles("/businesspresentation.properties", "questionSelectAsk1", "CONF_HOME"));
        list.add(ReadProperties.ReadPropertiesFromfiles("/businesspresentation.properties", "questionSelectAsk2", "CONF_HOME"));
        list.add(ReadProperties.ReadPropertiesFromfiles("/businesspresentation.properties", "questionSelectAsk3", "CONF_HOME"));
        list.add(ReadProperties.ReadPropertiesFromfiles("/businesspresentation.properties", "questionSelectAsk4", "CONF_HOME"));
        Map map = new HashMap();
        map.put("QuestionMustAsk1", new String(ReadProperties.ReadPropertiesFromfiles("/businesspresentation.properties", "questionMustAsk1", "CONF_HOME").getBytes("utf-8"), "utf-8"));
        map.put("QuestionMustAsk2", new String(ReadProperties.ReadPropertiesFromfiles("/businesspresentation.properties", "questionMustAsk2", "CONF_HOME").getBytes("utf-8"), "utf-8"));
        map.put("QuestionMustAsk3", new String(ReadProperties.ReadPropertiesFromfiles("/businesspresentation.properties", "QuestionMustAsk3", "CONF_HOME").getBytes("utf-8"), "utf-8"));
        System.out.println(list);
        System.out.println(map);
        System.out.println(ReadProperties.ReadPropertiesFromfiles("/businesspresentation.properties", "businesspresentation", "CONF_HOME"));
    }

    @Test
    public void stringBuilderAndJSONObjectTest() {
        String result = "{\n" +
                "    \"code\": \"10412\",\n" +
                "    \"message\": \"合同信息不一致\",\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"data\": [\n" +
                "                {\n" +
                "                    \"key\": \"mrtgRgstPlc\",\n" +
                "                    \"desc\": \"抵押登记地点\",\n" +
                "                    \"old\": \"铁门关市\",\n" +
                "                    \"new\": \"铁门关市tt\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"vhclLndAmt\",\n" +
                "                    \"desc\": \"vhclLndAmt\",\n" +
                "                    \"old\": \"73000.00\",\n" +
                "                    \"new\": \"73000.00tt\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"lndAmtLower\",\n" +
                "                    \"desc\": \"借款金额小写\",\n" +
                "                    \"old\": \"75000.00\",\n" +
                "                    \"new\": \"75000.00tt\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"lndAmtUp\",\n" +
                "                    \"desc\": \"借款金额大写\",\n" +
                "                    \"old\": \"柒万伍仟元整\",\n" +
                "                    \"new\": \"柒万伍仟元整tt\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"applSeq\": 9255535,\n" +
                "            \"ectrName\": \"借款及抵押合同\",\n" +
                "            \"ectrDefCde\": \"00000011\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"data\": [\n" +
                "                {\n" +
                "                    \"key\": \"mrtgRgstPlc\",\n" +
                "                    \"desc\": \"姓名\",\n" +
                "                    \"old\": \"旧的\",\n" +
                "                    \"new\": \"新的\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"vhclLndAmt\",\n" +
                "                    \"desc\": \"vhclLndAmt\",\n" +
                "                    \"old\": \"73000.00\",\n" +
                "                    \"new\": \"73000.00tt\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"lndAmtLower\",\n" +
                "                    \"desc\": \"借款金额小写\",\n" +
                "                    \"old\": \"75000.00\",\n" +
                "                    \"new\": \"75000.00tt\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"lndAmtUp\",\n" +
                "                    \"desc\": \"借款金额大写\",\n" +
                "                    \"old\": \"柒万伍仟元整\",\n" +
                "                    \"new\": \"柒万伍仟元整tt\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"applSeq\": 9255535,\n" +
                "            \"ectrName\": \"长安信托个人汽车消费借款及抵押合同-盛京联贷版\",\n" +
                "            \"ectrDefCde\": \"00000011\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(result);
        StringBuilder diffDesc = new StringBuilder("(");
        String sign = null;
        String same = null;
        if ("0".equals(jsonObject.getString("code"))) {
            logger.info("面签提交时校验电子协议一致！");
            sign = "Y";
            same = "Y";
        } else {
            logger.info("面签提交时校验电子协议不一致！code：" + jsonObject.getString("code"));
            same = "N";
            if ("10105".equals(jsonObject.getString("code"))) {
                sign = "N";
            } else {
                sign = "Y";
            }
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.size(); i++) {
                String ectrName = jsonArray.getJSONObject(i).getString("ectrName");
                //只取前后合同要素不同中的第一项不同进行提示
                String descString = jsonArray.getJSONObject(i).getJSONArray("data").getJSONObject(0).getString("desc");
                String oldString = jsonArray.getJSONObject(i).getJSONArray("data").getJSONObject(0).getString("old");
                String newString = jsonArray.getJSONObject(i).getJSONArray("data").getJSONObject(0).getString("new");
                diffDesc.append(i + 1 + "." + ectrName + "合同要素字段：").append(descString).append(",旧值为：" + oldString).append(";新值为：" + newString + "。");
            }
            diffDesc.append(")");
        }
        //判断从电子签约获取电子协议是否一致逻辑
        if ("N".equals(sign)) {
            logger.info("applSeq:" + "666" + ",电子协议未签署");
        }
        if ("N".equals(same)) {
            logger.info("applSeq:" + "666" + ",电子协议内容与业务数据不一致" + diffDesc.toString());
        }
    }
}
