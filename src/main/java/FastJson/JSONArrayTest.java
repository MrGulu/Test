package FastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JSONArrayTest {
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
}
