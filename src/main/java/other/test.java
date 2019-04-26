package other;

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
}
