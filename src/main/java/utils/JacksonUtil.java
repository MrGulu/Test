package utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * 
  * @ClassName(类名)      : JacksonUtil
  * @Description(描述)    : Jackson工具类---json和对象的互转
  * @author(作者)         ：Cola
  * @date (开发日期)      ：2017-8-8 下午4:23:11
  *
 */
public class JacksonUtil {
	public  static ObjectMapper objectMapper;

	
	/**
	 * 
	 * @Description(功能描述)    :  将JSON字符串转换为JavaBean
	 * @author(作者)         ：Cola
	 * @date (开发日期)      ：2017-8-8 下午4:23:11
	 * @param jsonStr
	 * @param valueType
	 * @return  T
	 */
	public static <T> T jsonToObject(String jsonStr, Class<T> valueType) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @Description(功能描述)    :  JavaBean转Json
	 * @author(作者)         ：Cola
	 * @date (开发日期)      ：2017-8-8 下午4:23:11
	 * @param object
	 * @return  String
	 */
	public static String ObjectToJson(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Description(功能描述)    :  json转map
	 * @author(作者)         ：Cola
	 * @date (开发日期)      ：2017-8-8 下午4:23:11
	 * @exception                : 
	 * @param json
	 * @return  Map<String,Object>
	 */
	public static Map<String, Object> jsonToMap(String json){
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		try {
			return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Description(功能描述)    :  map转json
	 * @author(作者)         ：Cola
	 * @date (开发日期)      ：2017-8-8 下午4:23:11
	 * @exception                : 
	 * @param map
	 * @return  String
	 */
	public static String mapToJson(Map<String, Object> map){
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Description(功能描述)    :  json转list
	 * @author(作者)         ：Cola
	 * @date (开发日期)      ：2017-8-8 下午4:23:11
	 * @exception                : 
	 * @param json
	 * @param collectionClass
	 * @param elementClasses
	 * @return  List<?>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonToCollection(String json,Class<?> collectionClass, Class<T> elementClasses){
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		JavaType javaType = getCollectionType(collectionClass, elementClasses);
		List<T> list =null;
		try {
			list = (List<T>)objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list ;
	}
	
	/**
	 * 
	 * @Description(功能描述)    :  获取泛型的Collection Type  
	 * @author(作者)        	 ：Cola
	 * @date (开发日期)      	：2017-8-8 下午4:23:11
	 * @exception                : 
	 * @param collectionClass
	 * @param elementClasses
	 * @return  JavaType
	 */
	 @SuppressWarnings("deprecation")
	private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
		 if (objectMapper == null) {
				objectMapper = new ObjectMapper();
			}
		 return  objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses); 
	 }
}
