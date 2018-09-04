package FastJson.test1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import org.junit.Test;

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
        //多个实体类转换为json数组形式的string
        String bookStr = JSON.toJSONString(bookList);
        //String转化为List<Book>
        List<Book> bookList3 = JSON.parseArray(bookStr,Book.class);

        System.out.println();
    }


    /**
     * String转化为数组
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
}
