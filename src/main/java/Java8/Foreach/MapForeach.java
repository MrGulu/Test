package Java8.Foreach;

import java.util.HashMap;
import java.util.Map;

public class MapForeach {
    public static void main(String[] args) {
//        1.1 正常方式遍历Map
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }


//        1.2 使用Java8的foreach+lambda表达式遍历Map
        Map<String, Integer> items2 = new HashMap<>();
        items2.put("A", 10);
        items2.put("B", 20);
        items2.put("C", 30);
        items2.put("D", 40);
        items2.put("E", 50);
        items2.put("F", 60);

        items2.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));

        items2.forEach((k,v)->{
            System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){
                System.out.println("Hello E");
            }
        });
    }
}
