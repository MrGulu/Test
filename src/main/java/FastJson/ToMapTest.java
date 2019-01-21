package FastJson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
//@SuppressWarnings("unchecked")
public class ToMapTest {
    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "0000");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "test");
        map.put("data", jsonObject);
        System.out.println(map);
        System.out.println(map.get("code"));
        System.out.println(map.get("data"));
        Map<String, String> covertMap = (Map<String, String>) map.get("data");
        JSONObject jsonObject1 = (JSONObject) map.get("data");
        System.out.println(covertMap);
        System.out.println(jsonObject1);
        String msgMap = covertMap.get("msg");
        String msgJson = jsonObject1.get("msg").toString();
        System.out.println(msgMap);
        System.out.println(msgJson);
        Map<String, Object> covertMapObject = (Map<String, Object>) map.get("data");
        String msgMapObject = covertMapObject.get("msg").toString();
        System.out.println(msgMapObject);

    }
}
