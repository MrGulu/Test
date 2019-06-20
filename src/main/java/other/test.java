package other;

import base.domain.JsonResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JacksonUtil;
import utils.ReadProperties;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static constant.Constant.*;

public class test {
    public static final Logger logger = LoggerFactory.getLogger(test.class);

    public static void main(String[] args) {
//        String s = "admin";
//        System.out.println(Long.valueOf(s));
        Long l = 0L;
        System.out.println(l.equals(0L));
        logger.info("hello");

        try {
            String docCde = URLDecoder.decode("/%E8%BA%AB%E4%BB%BD%E8%AF%81%E6%98%8E%E6%9D%90%E6%96%99/%E8%BA%AB%E4%BB%BD%E8%AF%81/%E8%BA%AB%E4%BB%BD%E8%AF%81%E6%AD%A3%E9%9D%A2", "UTF-8");
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
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapNull = null;
        Map<String, Object> mapTwo = new HashMap<>();
        ;
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

    @Test
    public void testStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("fdsfsdx新知就只。");
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ";");
        System.out.println(stringBuilder);
    }

    @Test
    @SuppressWarnings("all")
    public void testContract() {
        String s = "{\n" +
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
                "            \"ectrName\": \"中航信托个人汽车消费合同\",\n" +
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
                "                    \"new\": \"73000.00tttt\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"lndAmtLower\",\n" +
                "                    \"desc\": \"借款金额小写\",\n" +
                "                    \"old\": \"75000.00\",\n" +
                "                    \"new\": \"75000.00tttt\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"lndAmtUp\",\n" +
                "                    \"desc\": \"借款金额大写\",\n" +
                "                    \"old\": \"柒万伍仟元整\",\n" +
                "                    \"new\": \"柒万伍仟元整tttt\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"applSeq\": 9255535,\n" +
                "            \"ectrName\": \"长安信托个人汽车消费借款及抵押合同-盛京联贷版\",\n" +
                "            \"ectrDefCde\": \"00000011\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        StringBuilder diffDesc = new StringBuilder();
        List<String> diffList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject = JSON.parseObject(s);
        JSONArray data1 = jsonObject.getJSONArray("data");
        //方式一 平均220ms
        data1.forEach(x -> {
            String s1 = JSON.toJSONString(x);
            JSONObject jsonObject1 = JSON.parseObject(s1);
            String ectrName = jsonObject1.getString("ectrName");
            diffDesc.append(ectrName).append("合同要素字段：");
            JSONArray data2 = jsonObject1.getJSONArray("data");
            data2.forEach(y -> {
                String s2 = JSON.toJSONString(y);
                JSONObject jsonObject2 = JSON.parseObject(s2);
                String descString = jsonObject2.getString("desc");
                String oldString = jsonObject2.getString("old");
                String newString = jsonObject2.getString("new");
                diffDesc.append(descString).append(",旧值为：").append(oldString).append(",新值为：").append(newString).append("。");
            });
            diffDesc.replace(diffDesc.length() - 1, diffDesc.length(), ";");
        });
        //方式二 平均220ms-240ms
        for (int i = 0; i < data1.size(); i++) {
            String ectrName = data1.getJSONObject(i).getString("ectrName");
            diffDesc.append(ectrName).append("合同要素字段：");
            JSONArray data2 = data1.getJSONObject(i).getJSONArray("data");
            data2.forEach(y -> {
                String s1 = JSON.toJSONString(y);
                JSONObject jsonObject1 = JSON.parseObject(s1);
                String descString = jsonObject1.getString("desc");
                String oldString = jsonObject1.getString("old");
                String newString = jsonObject1.getString("new");
                diffDesc.append(descString).append(",旧值为：").append(oldString).append(",新值为：").append(newString).append("。");
            });
            diffDesc.replace(diffDesc.length() - 1, diffDesc.length(), ";");
        }
        //方式三 平均190ms-200ms
        Consumer<JSONArray> consumer = v -> {
            for (int j = 0; j < v.size(); j++) {
                String descString = v.getJSONObject(j).getString("desc");
                String oldString = v.getJSONObject(j).getString("old");
                String newString = v.getJSONObject(j).getString("new");
                diffDesc.append(descString).append(",旧值为：").append(oldString).append(",新值为：").append(newString).append("。");
            }
            diffDesc.replace(diffDesc.length() - 1, diffDesc.length(), ";");
        };
        for (int i = 0; i < data1.size(); i++) {
            String ectrName = data1.getJSONObject(i).getString("ectrName");
            diffDesc.append(ectrName).append("合同要素字段：");
            JSONArray data2 = data1.getJSONObject(i).getJSONArray("data");
            consumer.accept(data2);
        }
        //方式四 平均203ms
        for (int i = 0; i < data1.size(); i++) {
            String ectrName = data1.getJSONObject(i).getString("ectrName");
            diffDesc.append(ectrName).append("合同要素字段：");
            JSONArray data2 = data1.getJSONObject(i).getJSONArray("data");
            for (int j = 0; j < data2.size(); j++) {
                String descString = data2.getJSONObject(j).getString("desc");
                String oldString = data2.getJSONObject(j).getString("old");
                String newString = data2.getJSONObject(j).getString("new");
                diffDesc.append(descString).append(",旧值为：").append(oldString).append(",新值为：").append(newString).append("。");
            }
            diffDesc.replace(diffDesc.length() - 1, diffDesc.length(), ";");
        }
        //处理结果
        Collections.addAll(diffList, diffDesc.toString().split(";"));
        System.out.println(diffList);
        System.out.println(JacksonUtil.objectToJson(diffList));
    }

    @Test
    @SuppressWarnings("all")
    public void testContract2() {
        String s = "{\n" +
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
                "            \"ectrName\": \"中航信托个人汽车消费合同\",\n" +
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
                "                    \"new\": \"73000.00tttt\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"lndAmtLower\",\n" +
                "                    \"desc\": \"借款金额小写\",\n" +
                "                    \"old\": \"75000.00\",\n" +
                "                    \"new\": \"75000.00tttt\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"lndAmtUp\",\n" +
                "                    \"desc\": \"借款金额大写\",\n" +
                "                    \"old\": \"柒万伍仟元整\",\n" +
                "                    \"new\": \"柒万伍仟元整tttt\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"applSeq\": 9255535,\n" +
                "            \"ectrName\": \"长安信托个人汽车消费借款及抵押合同-盛京联贷版\",\n" +
                "            \"ectrDefCde\": \"00000011\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        StringBuilder diffDesc = new StringBuilder();
        List<String> diffList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject = JSON.parseObject(s);
        JSONArray data1 = jsonObject.getJSONArray("data");
        //方式三 平均190ms-200ms
        Consumer<JSONArray> consumer = v -> {
            for (int j = 0; j < v.size(); j++) {
                String descString = v.getJSONObject(j).getString("desc");
                String oldString = v.getJSONObject(j).getString("old");
                String newString = v.getJSONObject(j).getString("new");
                diffDesc.append(descString).append(",旧值为：").append(oldString).append(",新值为：").append(newString).append("。");
            }
            diffDesc.replace(diffDesc.length() - 1, diffDesc.length(), ";");
        };
        for (int i = 0; i < data1.size(); i++) {
            String ectrName = data1.getJSONObject(i).getString("ectrName");
            diffDesc.append(ectrName).append("合同要素字段：");
            JSONArray data2 = data1.getJSONObject(i).getJSONArray("data");
            consumer.accept(data2);
        }
        //处理结果
        Collections.addAll(diffList, diffDesc.toString().split(";"));
        JSONArray jsonArray = new JSONArray();
        Map map = new HashMap();
        List rtnList = new ArrayList();
        for (int i = 0; i < diffList.size(); i++) {
            List list = new ArrayList();
            list.add(diffList.get(i));
            map.put(i, list);
            rtnList.add(list);
        }
        System.out.println(diffList);
        System.out.println(JacksonUtil.objectToJson(diffList));
        System.out.println(JacksonUtil.mapToJson(map));
        System.out.println(JacksonUtil.objectToJson(rtnList));
    }

    /**
     * --list
     * {
     * "code": "1231",
     * "message": "合同信息不一致",
     * "data": [
     * "aaa",
     * "bbb"
     * ]
     * }
     * <p>
     * --map
     * {
     * "code": "1231",
     * "message": "合同信息不一致",
     * "data": {
     * "a": 2,
     * "b": 3,
     * "c": 4
     * }
     * }
     * <p>
     * --list中map
     * {
     * "code": "1231",
     * "message": "合同信息不一致",
     * "data": [
     * {
     * "a": 2,
     * "b": 3,
     * "c": 4
     * }
     * ]
     * }
     * <p>
     * --复杂类型
     * --第一个data为list，包含多个map，每个map中都有一个data也为list，里面包含多个map。
     * {
     * "code": "1231",
     * "message": "合同信息不一致",
     * "data": [
     * {
     * "applSeq": "9274245",
     * "data": [
     * {
     * "new": "天津市",
     * "old": "北京市",
     * "key": "mrtgRgstPlc",
     * "desc": "抵押登记地点"
     * },
     * {
     * "new": "张三new",
     * "old": "张三old",
     * "key": "name",
     * "desc": "客户姓名"
     * },
     * {
     * "new": "150000.00",
     * "old": "140001.00",
     * "key": "vhclPrc",
     * "desc": "车辆价格"
     * }
     * ],
     * "ectrName": "长安信托-九江借款抵押合同（无车架号）",
     * "ectrDefCde": "00000031"
     * }
     * ]
     * }
     */
    @Test
    public void testRtnJson() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode("1231").setMessage("合同信息不一致");
        List<Object> data1 = new ArrayList<>();
        HashMap<String, Object> data1Map = new HashMap<>();
        List<Map> data2 = new ArrayList<>();
        Map<String, Object> data2Map1 = new HashMap<>();
        data2Map1.put("key", "mrtgRgstPlc");
        data2Map1.put("desc", "抵押登记地点");
        data2Map1.put("old", "北京市");
        data2Map1.put("new", "天津市");
        Map<String, Object> data2Map2 = new HashMap<>();
        data2Map2.put("key", "name");
        data2Map2.put("desc", "客户姓名");
        data2Map2.put("old", "张三old");
        data2Map2.put("new", "张三new");
        Map<String, Object> data2Map3 = new HashMap<>();
        data2Map3.put("key", "vhclPrc");
        data2Map3.put("desc", "车辆价格");
        data2Map3.put("old", "140001.00");
        data2Map3.put("new", "150000.00");
        data2.add(data2Map1);
        data2.add(data2Map2);
        data2.add(data2Map3);
        data1Map.put("data", data2);
        data1Map.put("applSeq", "9274245");
        data1Map.put("ectrName", "长安信托-九江借款抵押合同（无车架号）");
        data1Map.put("ectrDefCde", "00000031");
        data1.add(data1Map);
        jsonResponse.setData(data1);
        System.out.println(JacksonUtil.objectToJson(jsonResponse));
    }


    @Test
    @SuppressWarnings("all")
    public void testJava8() {
        String result = "{\n" +
                "\t\"code\": \"0000\",\n" +
                "\t\"message\": \"ok\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"reqsys\": \"cfs\",\n" +
                "\t\t\"prod\": {\n" +
                "\t\t\t\"prodBrandCde\": \"hello\",\n" +
                "\t\t\t\"insurances\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"years\": \"1年\",\n" +
                "\t\t\t\t\t\"priceRange\": \"30万以下\",\n" +
                "\t\t\t\t\t\"premium\": \"1000.00\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"years\": \"2年\",\n" +
                "\t\t\t\t\t\"priceRange\": \"50万以下\",\n" +
                "\t\t\t\t\t\"premium\": \"2000.00\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        String imeiCde = null;
        String flag = "1";
        String years = "1年";
        String priceRange = "30万以下";
        final String[] premium = {null};
        JSONObject jsonObject;
        jsonObject = JSON.parseObject(result).getJSONObject("data").getJSONObject("prod");
        System.out.println("获取insurances：\n" + jsonObject.toJSONString());

        JSONArray jsonArray = jsonObject.getJSONArray("insurances");
        List<String> list = new ArrayList<>();
        switch (flag) {
            case FLAG_ONE:
                jsonArray.stream()
                        .map(o -> ((JSONObject) o).getString("years"))
                        .forEach(s -> list.add(s));
                System.out.println(list);
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setCode("111");
                jsonResponse.setMessage("jjj");
                jsonResponse.setData(list);
                System.out.println(JacksonUtil.objectToJson(jsonResponse));
                break;
            case FLAG_TWO:
                jsonArray.stream()
                        .map(o -> ((JSONObject) o).getString("priceRange"))
                        .forEach(s -> list.add(s));
                System.out.println(list);
                JsonResponse jsonResponse2 = new JsonResponse();
                jsonResponse2.setCode("111");
                jsonResponse2.setMessage("jjj");
                jsonResponse2.setData(list);
                System.out.println(JacksonUtil.objectToJson(jsonResponse2));
                break;
            case FLAG_THREE:
                String finalYears = years;
                String finalPriceRange = priceRange;
                Optional<Object> first = jsonArray.stream()
                        .filter(o -> finalYears.equals(((JSONObject) o).getString("years"))
                                && finalPriceRange.equals(((JSONObject) o).getString("priceRange")))
                        .findFirst();
                first.ifPresent(o -> premium[0] = ((JSONObject) o).getString("premium"));
                System.out.println(premium[0]);
                JsonResponse jsonResponse3 = new JsonResponse();
                jsonResponse3.setCode("111");
                jsonResponse3.setMessage("jjj");
                jsonResponse3.setData(premium[0]);
                System.out.println(JacksonUtil.objectToJson(jsonResponse3));
                break;
            default:
                logger.error("非法flag值！");
                break;
        }
    }

    @Test
    public void testEncoding() throws UnsupportedEncodingException {
        String s = "30ä¸\u0087ä»¥ä¸\u008B";
        //如果字符串已经在请求的过程中被通过ISO-8859-1编码转换了（以这个编码方式转成字节数组），那么这时候需要还是通过这个编码方式还原字节数组，然后使用UTF-8方式编码，这样中文就不会乱码了
        String str = new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String str2 = new String(s.getBytes(), StandardCharsets.UTF_8);
        System.out.println(str);
        System.out.println(str2);

        //如果本身就是汉字，经过下面的方式是不可以的
        String s2 = "30万以下";
        String str3 = new String(s2.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println(str3);

    }

    @Test
    public void testBigDecimal() {
        String s = "1000.006";
        //并不会进行精度的控制，只是单纯把String类型转成BigDecimal，但是这样的话，如果数据库中指定数据类型为NUMBER(18,2)
        //设置为两位小数的话，入库就会出问题
        BigDecimal bigDecimal = new BigDecimal(s);
        //会返回一个新的BigDecimal对象
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        System.out.println(bigDecimal);
        System.out.println(bigDecimal1);


        System.out.println(formatToNumber(new BigDecimal("3.435")));
        System.out.println(formatToNumber(new BigDecimal(0)));
        System.out.println(formatToNumber(new BigDecimal("0.00")));
        System.out.println(formatToNumber(new BigDecimal("0.001")));
        System.out.println(formatToNumber(new BigDecimal("0.006")));
        System.out.println(formatToNumber(new BigDecimal("0.206")));
        System.out.println(formatToNumber(new BigDecimal("2000.00")));

    }

    /**
     * @param obj 传入的小数
     * @return
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (obj.compareTo(BigDecimal.ZERO) == 0) {
            return "0.00";
        } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
            return "0" + df.format(obj).toString();
        } else {
            return df.format(obj).toString();
        }
    }

    @Test
    public void testListNull() {
        List<String> list = null;
        for (String s :
                list) {
            System.out.println(s);
        }
    }

    /**
     * 测试jackson工具
     * 最后要转成json字符串的方法，最后调用的是同一个方法：
     * return objectMapper.writeValueAsString(Object obj);
     * 可以转化任何的java类型
     * 如果是json字符串转化成其他类型，就需要使用具体的方法，转成实体类、容器类等。
     */
    //根据list中map中的某个值对list中的map元素进行排序，字符串类型的"01"、"02"、"03"升序~
    @Test
    public void testComparator() {
        String s = "[\n" +
                "\t{\n" +
                "\t\t\"custName\": \"秦谦谦\",\n" +
                "\t\t\"list\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"signCde\": \"318bd1e242581c590ef0793b7a5e14d6\",\n" +
                "\t\t\t\t\"identNo\": \"370828199202261626\",\n" +
                "\t\t\t\t\"signStatus\": \"4\",\n" +
                "\t\t\t\t\"econtractCde\": null,\n" +
                "\t\t\t\t\"econtractName\": \"长安信托个人汽车消费借款及抵押合同-独资版\",\n" +
                "\t\t\t\t\"econtractDefCde\": null,\n" +
                "\t\t\t\t\"ectrDefCde\": \"00000009\",\n" +
                "\t\t\t\t\"econtractUrl\": \"http://openapi.bestsign.info:80/fe/intf/v2/#/previewSign?developerId=1841170298758496808&rtick=1560843123651978&signType=token&sign=eyJkZXZlbG9wZXJJZCI6IjE4NDExNzAyOTg3NTg0OTY4MDgiLCJjYXRhbG9nSWQiOiI1OTEyMDczNzcyMzExNTExNzUzIiwiY29udHJhY3RJZCI6IiIsImV4cGlyZUF0IjoiMTU2MTQ0NzkyMyIsImFjY291bnQiOiI3NDAyMDk5NyJ9LjE1NjA4NDMxMjM2NTAyMTkz.51551b22fdf938d6e3a07e0588026cda&catalogId=5912073772311511753&signerAccount=74020997&dpi=240&sid=\",\n" +
                "\t\t\t\t\"status\": \"1\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"signCde\": \"318bd1e242581c590ef0793b7a5e14d6\",\n" +
                "\t\t\t\t\"identNo\": \"370828199202261626\",\n" +
                "\t\t\t\t\"signStatus\": \"4\",\n" +
                "\t\t\t\t\"econtractCde\": null,\n" +
                "\t\t\t\t\"econtractName\": \"长安信托-委托扣款授权书\",\n" +
                "\t\t\t\t\"econtractDefCde\": null,\n" +
                "\t\t\t\t\"ectrDefCde\": \"00000018\",\n" +
                "\t\t\t\t\"econtractUrl\": \"http://openapi.bestsign.info:80/fe/intf/v2/#/previewSign?developerId=1841170298758496808&rtick=1560843123862845&signType=token&sign=eyJkZXZlbG9wZXJJZCI6IjE4NDExNzAyOTg3NTg0OTY4MDgiLCJjYXRhbG9nSWQiOiIyNjcwMDUyMzAyNjg2Njc0OTMiLCJjb250cmFjdElkIjoiIiwiZXhwaXJlQXQiOiIxNTYxNDQ3OTIzIiwiYWNjb3VudCI6Ijc0MDIwOTk3In0uMTU2MDg0MzEyMzg2MjEyMDE_.8932fcde7ed3727e46934b477c612de0&catalogId=267005230268667493&signerAccount=74020997&dpi=240&sid=\",\n" +
                "\t\t\t\t\"status\": \"1\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"apptSeq\": \"9286744\",\n" +
                "\t\t\"apptTyp\": \"01\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"custName\": \"测试哈哈哈\",\n" +
                "\t\t\"list\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"signCde\": \"318bd1e242581c590ef0793b7a5e14d6\",\n" +
                "\t\t\t\t\"identNo\": \"370283199501114310\",\n" +
                "\t\t\t\t\"signStatus\": \"4\",\n" +
                "\t\t\t\t\"econtractCde\": null,\n" +
                "\t\t\t\t\"econtractName\": \"长安信托个人汽车消费借款及抵押合同-独资版\",\n" +
                "\t\t\t\t\"econtractDefCde\": null,\n" +
                "\t\t\t\t\"ectrDefCde\": \"00000009\",\n" +
                "\t\t\t\t\"econtractUrl\": \"http://openapi.bestsign.info:80/fe/intf/v2/#/previewSign?developerId=1841170298758496808&rtick=1560843147911432&signType=token&sign=eyJkZXZlbG9wZXJJZCI6IjE4NDExNzAyOTg3NTg0OTY4MDgiLCJjYXRhbG9nSWQiOiI1OTEyMDczNzcyMzExNTExNzUzIiwiY29udHJhY3RJZCI6IiIsImV4cGlyZUF0IjoiMTU2MTQ0Nzk0NyIsImFjY291bnQiOiI3NDAyMDk5NyJ9LjE1NjA4NDMxNDc5MTE1NDEx.0a0d73b4907fd3827fe1294e0e01f249&catalogId=5912073772311511753&signerAccount=74020997&dpi=240&sid=\",\n" +
                "\t\t\t\t\"status\": \"1\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"apptSeq\": \"9286755\",\n" +
                "\t\t\"apptTyp\": \"03\"\n" +
                "\t},\n" +
                "\t\t{\n" +
                "\t\t\"custName\": \"唐文龙\",\n" +
                "\t\t\"list\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"signCde\": \"318bd1e242581c590ef0793b7a5e14d6\",\n" +
                "\t\t\t\t\"identNo\": \"370283199501114310\",\n" +
                "\t\t\t\t\"signStatus\": \"4\",\n" +
                "\t\t\t\t\"econtractCde\": null,\n" +
                "\t\t\t\t\"econtractName\": \"长安信托个人汽车消费借款及抵押合同-独资版\",\n" +
                "\t\t\t\t\"econtractDefCde\": null,\n" +
                "\t\t\t\t\"ectrDefCde\": \"00000009\",\n" +
                "\t\t\t\t\"econtractUrl\": \"http://openapi.bestsign.info:80/fe/intf/v2/#/previewSign?developerId=1841170298758496808&rtick=1560843147911432&signType=token&sign=eyJkZXZlbG9wZXJJZCI6IjE4NDExNzAyOTg3NTg0OTY4MDgiLCJjYXRhbG9nSWQiOiI1OTEyMDczNzcyMzExNTExNzUzIiwiY29udHJhY3RJZCI6IiIsImV4cGlyZUF0IjoiMTU2MTQ0Nzk0NyIsImFjY291bnQiOiI3NDAyMDk5NyJ9LjE1NjA4NDMxNDc5MTE1NDEx.0a0d73b4907fd3827fe1294e0e01f249&catalogId=5912073772311511753&signerAccount=74020997&dpi=240&sid=\",\n" +
                "\t\t\t\t\"status\": \"1\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"apptSeq\": \"9286755\",\n" +
                "\t\t\"apptTyp\": \"02\"\n" +
                "\t}\n" +
                "]";
        Set<Map> set = JacksonUtil.jsonToCollectionSet(s, Set.class, Map.class);
        System.out.println(set);
        System.out.println(JacksonUtil.objectToJson(set));
        System.out.println("*************************************");

        List<Map> maps = JacksonUtil.jsonToCollectionList(s, List.class, Map.class);
        maps = maps.stream()
                .sorted(Comparator.comparing(map -> map.get("apptTyp").toString()))
                .collect(Collectors.toList());
        System.out.println("*************************************");
        System.out.println(JacksonUtil.objectToJson(maps));
    }


}
