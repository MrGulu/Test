package jsonParse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import utils.JacksonUtil;

import java.util.HashMap;
import java.util.Map;

public class FastJsonTest {
    /**
     * 使用jackson转换map为json串，和fastjson转换本身为json串时的差异：
     * 如果value=null时，jackson在转成json串时也会体现，
     * 而用fastjson时，会忽略value=null的字段。
     * 运行结果如下：
     * {"aa":"2","bb":"fd","cc":"","dd":"fgdg","key":null}
     * {"aa":"2","bb":"fd","cc":"","dd":"fgdg"}
     */
    @Test
    public void test0() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", null);
        map.put("aa", "2");
        map.put("bb", "fd");
        map.put("cc", "");
        map.put("dd", "fgdg");
        String mapToJson = JacksonUtil.mapToJson(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", null);
        jsonObject.put("aa", "2");
        jsonObject.put("bb", "fd");
        jsonObject.put("cc", "");
        jsonObject.put("dd", "fgdg");
        String toJSONString = jsonObject.toJSONString();
        //通过jackson和fastjson将map和jsonobject转成json串
        System.out.println(mapToJson);
        System.out.println(toJSONString);
        System.out.println(JSON.toJSONString(map));

        //通过jackson和fastjson将json串转成map和jsonobject
        /**
         * map          {aa=2, bb=fd, cc=, dd=fgdg, key=null}
         * jsonobject   {"aa":"2","bb":"fd","cc":"","dd":"fgdg"}
         */
        System.out.println(JacksonUtil.jsonToMap("{\"aa\":\"2\",\"bb\":\"fd\",\"cc\":\"\",\"dd\":\"fgdg\",\"key\":null}"));
        System.out.println(JSON.parseObject("{\"aa\":\"2\",\"bb\":\"fd\",\"cc\":\"\",\"dd\":\"fgdg\",\"key\":null}"));
    }


    /**
     * 1.JSONObject实现了Map接口，所以可以使用Map接收，但是实质上还是JSONObject类型的
     * 2.JSONObject底层也是根据Map实现的，可以认为是Map的升级版；其内部有一个final类型的
     *   Map成员，在进行一系列的操作时，操作的是这个Map；在new对象时，无参构造函数是将Map
     *   使用LinkedHashMap或者HashMap实例化（不需排序时，默认用的HashMap），并且跟Map
     *   一样，也是使用默认的初始容量：16
     */
    @Test
    public void test() {
        String s = "[{\"name\":\"jack\",\"age\":\"18\"},{\"name\":\"tom\",\"age\":\"19\"}]";
        JSONArray obj1 = JSON.parseArray(s);
        System.out.println(obj1);
        Map map = (Map) obj1.get(0);
        JSONObject jsonObject = obj1.getJSONObject(1);
        System.out.println(map.get("name"));
        if (map.get("name") instanceof String) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        System.out.println(jsonObject.get("name"));

        Map map1 = new HashMap();
        map1.put("key", "value");
        String value = (String) map1.get("key");
        System.out.println(value);
    }

    @Test
    public void test1() {
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
