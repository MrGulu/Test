package reflect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import custom.exception.BusinessException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author tangwenlong
 * @date 2019-12-18
 */
public class MapReflect {

    /**
     * 匿名内部类双括号初始化（double brace initialization）技巧
     */
    @SuppressWarnings("unchecked")
    private static HashSet rulesSet = new HashSet() {
        private static final long serialVersionUID = 3506905171987504870L;

        //内层括号定义了一个该匿名子类的构造块（构造对象时会自动执行的代码块）
        {
            add("java.util.Date");
            add("java.math.BigDecimal");
            add("java.math.BigInteger");
            add("java.lang.Integer");
            add("java.lang.Long");
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(MapReflect.class);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> T map2Obj(Map<String, Object> map, Class<T> clazz) {
        if (Objects.isNull(map) || map.isEmpty()) {
            return null;
        }
        if (clazz == null) {
            throw new IllegalArgumentException("clazz is null");
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                    continue;
                }
                //暴力访问
                field.setAccessible(true);
                //实体Obj中域的java类型
                String fieldTypeName = field.getType().getName();
                //需要转换的类型处理
                if (rulesSet.contains(fieldTypeName)) {
                    Object tmpMapValue = map.get(field.getName());
                    if (Objects.nonNull(tmpMapValue)) {
                        //map中value的java类型
                        String tmpTypeName = tmpMapValue.getClass().getTypeName();
                        //将map中的value转成Obj中对应的java类型（若map中value不一致）
                        if (!fieldTypeName.equals(tmpTypeName)) {
                            String tmpMapValueStr = String.valueOf(tmpMapValue);
                            if ("null".equalsIgnoreCase(tmpMapValueStr)) {
                                field.set(obj, null);
                            } else {
                                switch (fieldTypeName) {
                                    case "java.util.Date":
                                        field.set(obj, new Date(Long.parseLong(tmpMapValueStr)));
                                        break;
                                    case "java.math.BigDecimal":
                                        field.set(obj, new BigDecimal(tmpMapValueStr));
                                        break;
                                    case "java.math.BigInteger":
                                        field.set(obj, new BigInteger(tmpMapValueStr));
                                        break;
                                    case "java.lang.Integer":
                                        field.set(obj, Integer.parseInt(tmpMapValueStr));
                                        break;
                                    case "java.lang.Long":
                                        field.set(obj, Long.parseLong(tmpMapValueStr));
                                        break;
                                    default:
                                        logger.error("非法参数[{}]！", fieldTypeName);
                                        throw new BusinessException("非法参数" + fieldTypeName);
                                }
                            }
                        } else {
                            field.set(obj, tmpMapValue);
                        }
                    }
                } else {
                    Object param = map.get(field.getName());
                    if (Objects.nonNull(param)) {
                        field.set(obj, param);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("map反射转换异常！", e);
            throw new BusinessException("map反射转换异常！");
        }
        return obj;
    }

    @Test
    public void testJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", new BigDecimal("123123"));
        String key = jsonObject.get("key").getClass().getTypeName();
        System.out.println(key);

        jsonObject.put("applSeq", "6666.66");
        jsonObject.put("applSeq2", new BigDecimal("6666.66"));

        jsonObject.put("typSeq", 123123);
        jsonObject.put("typSeq2", new Long("123123"));

        jsonObject.put("typVer", "4444");
        /**
         * 下面两种方式其实都是一样的，第一个5555从map中取出时会自动包装秤Integer类型
         */
        jsonObject.put("typVer2", 5555);
        jsonObject.put("typVer3", new Integer("6666"));

        jsonObject.put("instuCde", "1111");
        jsonObject.put("applCde", "2222");

        Appl appl = MapReflect.map2Obj(jsonObject, Appl.class);
        System.out.println(JSON.toJSON(appl));
    }

    @Test
    public void testMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", new BigDecimal("123123"));
        String key = map.get("key").getClass().getTypeName();
        System.out.println(key);

        map.put("applSeq", "6666.66");
        map.put("applSeq2", new BigDecimal("6666.66"));

        map.put("typSeq", 123123);
        map.put("typSeq2", new Long("123123"));

        map.put("typVer", "4444");
        /**
         * 下面两种方式其实都是一样的，第一个5555从map中取出时会自动包装秤Integer类型
         */
        map.put("typVer2", 5555);
        map.put("typVer3", new Integer("6666"));

        map.put("instuCde", "1111");
        map.put("applCde", "2222");

        Appl appl = MapReflect.map2Obj(map, Appl.class);
        System.out.println(JSON.toJSON(appl));
    }

    public static void main(String[] args) {
        String s = "111";
        BigInteger integer = new BigInteger(s);
        System.out.println(integer);


        String[] ss = new String[]{"aaa", "bbb"};
        System.out.println(ss.getClass().getTypeName());


        Integer aa = Integer.parseInt("444");
        System.out.println(aa.getClass().getTypeName());

        int i = 6;

        //illegal
//        Class<Appl> Class = Student.class;
        Class<Appl> Class = Appl.class;
        System.out.println(Class == Appl.class);

    }


    /**
     * 用于使用request.getparameterMap()方法时使用，但仍然是不完善的，所以
     * 一般使用上面的方法，在系统交互时一般使用json形式传输数据，可以将json转成map
     * 调用上面方法。
     * <p>
     * 或者使用SpringMVC时，直接使用@RequestBody Appl，使用框架直接将参数与实体类绑定起来。
     */
    @Test
    public void testGetParameterMap() {
        Map map = new HashMap(4);
        String[] a = new String[]{"aaaa"};
        map.put("ahrSeq", a);
        System.out.println(map2Object(map, Appl.class));
    }


    public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String filedTypeName = field.getType().getName();
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = String.valueOf(map.get(field.getName()));
                    if (datetimestamp.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, new Date(Long.parseLong(datetimestamp)));
                    }
                } else {
                    /**
                     * 传参过程中，后端使用request.getParameterMap()方法获取全部参数时，
                     * 获取的value是一个数组，并且可能是多个值的，因为传参过程中如果多次给同一个参数赋值，
                     * 则就会有多个，例如：?a=123&a=456
                     */
                    String param[] = (String[]) map.get(field.getName());
                    if (param != null && param.length > 0) {
                        field.set(obj, param[0]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}


