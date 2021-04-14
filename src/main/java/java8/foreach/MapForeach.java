package java8.foreach;

import java.util.HashMap;
import java.util.Map;

public class MapForeach {
    public static void main(String[] args) {
        /*1 正常方式遍历Map*/
        Map<String, Integer> items = new HashMap<>(8);
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);

        /*1.1 entrySet方式遍历Map*/
        for (Map.Entry<String, Integer> entry :
                items.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }
        System.out.println("--------------------------------------------------------");
        /*1.1 keySet方式遍历Map*/
        for (String s :
                items.keySet()) {
            System.out.println("Item : " + s + " Count : " + items.get(s));
        }

        System.out.println("--------------------------------------------------------");

        /*2 使用Java8的foreach+lambda表达式遍历Map*/
        Map<String, Integer> items2 = new HashMap<>(8);
        items2.put("A", 10);
        items2.put("B", 20);
        items2.put("C", 30);
        items2.put("D", 40);
        items2.put("E", 50);
        items2.put("F", 60);

        /*2.1 直接遍历*/
        items2.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));
        /*2.2 符合条件遍历*/
        items2.forEach((k, v) -> {
            System.out.println("Item : " + k + " Count : " + v);
            if ("E".equals(k)) {
                System.out.println("Hello E");
            }
        });
    }
}
