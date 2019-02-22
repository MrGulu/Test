package FastJson.test1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import java.util.*;

public class FastJsonTest {
    /**
     * String转化为实体类Book
     */
    @Test
    public void StrToObject() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"name\": \"教父三部曲\",");
        sb.append(" \"author\": \"马里奥·普佐\",");
        sb.append("  \"price\": \"100\"");
        sb.append("}");
        Book book = JSON.parseObject(sb.toString(), Book.class);
        System.out.println(book.toString());
    }
    /**
     * String转化为jsonObject
     */
    @Test
    public void StrToJsonObject() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"name\": \"教父三部曲\",");
        sb.append(" \"author\": \"马里奥·普佐\",");
        sb.append("  \"price\": \"100\"");
        sb.append("}");
        JSONObject jsonObj = JSON.parseObject(sb.toString());

        for(Map.Entry<String, Object> entry : jsonObj.entrySet()){
            System.out.println(entry.getKey()+"--"+entry.getValue());
        }
    }

    /**
     * 实体类转换为json
     * 多个实体类转换为json数组形式的string
     * String转化为List<Book>
     */
    @Test
    public void StrToListObject() {
        List<Book> bookList =  new ArrayList<>();
        Book book1 = new Book("教父三部曲","马里奥·普佐","100") ;
        Book book2 = new Book("天才在左疯子在右","高铭","30") ;

        bookList.add(book1);
        bookList.add(book2);
        //实体类转换为json
        String book1Str = JSON.toJSONString(book1);
        System.out.println(book1Str);
        //多个实体类转换为json数组形式的string
        String bookStr = JSON.toJSONString(bookList);
        System.out.println(bookStr);
        //String转化为List<Book>
        List<Book> bookList3 = JSON.parseArray(bookStr,Book.class);
        System.out.println(bookList3.toString());

        System.out.println();
    }


    /**
     * String转化为对象数组
     * String转化为ArrayList
     */
    @Test
    public void StrToArrayList() {
        StringBuilder sb = new StringBuilder();
        sb.append("[{");
        sb.append("\"name\": \"教父三部曲\",");
        sb.append(" \"author\": \"马里奥·普佐\",");
        sb.append("  \"price\": \"100\"");
        sb.append("}]");
        // String转化为数组
        Book[] arrBook = JSON.parseObject(sb.toString(),new TypeReference<Book[]>(){});
        List<Book> list = Arrays.asList(arrBook);
        for (int i = 0; i < arrBook.length; i++) {
            System.out.println(arrBook[i].toString());
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        // String转化为ArrayList
        ArrayList<Book> bookList=  JSON.parseObject(sb.toString(),new TypeReference<ArrayList<Book>>(){});

        for(Book book : bookList){
            System.out.println(book.toString());
        }
    }

    /**
     * map和json互相转换
     */
    @SuppressWarnings("unused")
    @Test
    public void mapTo(){
        Map<String,String> map = new HashMap<>();
        map.put("name", "教父三部曲");
        map.put("author", "马里奥·普佐");
        map.put("price", "100");
        //map转换为json
        String json = JSON.toJSONString(map);
        //json转换为map
        Map map1 = JSON.parseObject(json);
        Map<String,String> map2 = (Map<String,String>)JSON.parse(json);

    }

    @Test
    public void testDomain1() {
        Book book = new Book();
        book.setName("name");
        book.setAuthor("tang");
        book.setPrice("233.00");
        String apiCode = "MDDPCT1017";
        String applicationCode = "MDDP";
        JSONObject params = new JSONObject();
        params.put("apiCode",apiCode);
        params.put("applicationCode",applicationCode);
        params.put("interfaceType", "1016");
        params.put("data",book);
        System.out.println(params.toJSONString());

        JSONObject jsonObject = JSON.parseObject(params.toJSONString());
        System.out.println(jsonObject.getJSONObject("data").getString("name"));
    }

    /**
     * {"interfaceType":"1016","data":"{\"author\":\"tang\",\"name\":\"name\",\"price\":\"233.00\"}","apiCode":"MDDPCT1017","applicationCode":"MDDP"}
     * 如果像下面这样先把实体类toJSONString
     * 然后最后整体的JSONObject再一次toJSONString
     * 那么最后会出现如下形式的报文，发现data的部分不是想要的json形式啊？还是说还是可用的
     * 经过最后实验发现，是不可用的，无法将里面的data数据转换成JSONObject类型！
     * 只能将data取出（String），然后将这个String类型转成JSONObject类型，才可以取出相应的属性！
     */
    @Test
    public void testDomain2() {
        Book book = new Book();
        book.setName("name");
        book.setAuthor("tang");
        book.setPrice("233.00");
        String data = JSON.toJSONString(book);
        String apiCode = "MDDPCT1017";
        String applicationCode = "MDDP";
        JSONObject params = new JSONObject();
        params.put("apiCode",apiCode);
        params.put("applicationCode",applicationCode);
        params.put("interfaceType", "1016");
        params.put("data",data);
        String toJsonString = params.toJSONString();
        System.out.println(toJsonString);

        JSONObject jsonObject = JSON.parseObject(toJsonString);
//        System.out.println(jsonObject.getJSONObject("data").getString("name"));
        String dataString = jsonObject.getString("data");
        JSONObject jsonObject1 = JSON.parseObject(dataString);
        System.out.println(jsonObject1.getString("name"));
    }

    /**
     * data放入JSONObject类型数据
     * 这样最后paramstoJSONString没问题
     */
    @Test
    public void testJSONObject3() {

        String apiCode = "MDDPCT1017";
        String applicationCode = "MDDP";
        JSONObject data = new JSONObject();
        data.put("cooprAddr", "1");
        data.put("directBch", "2");
        data.put("busiContactName", "3");
        JSONObject params = new JSONObject();
        params.put("apiCode",apiCode);
        params.put("applicationCode",applicationCode);
        params.put("interfaceType", "1016");
        params.put("data",data);
        String toJsonString = params.toJSONString();
        System.out.println(toJsonString);

        JSONObject jsonObject = JSON.parseObject(toJsonString);
        System.out.println(jsonObject.getJSONObject("data").getString("cooprAddr"));
//        String dataString = jsonObject.getString("data");
//        JSONObject jsonObject1 = JSON.parseObject(dataString);
//        System.out.println(jsonObject1.getString("cooprAddr"));

    }

}
