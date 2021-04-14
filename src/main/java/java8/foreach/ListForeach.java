package java8.foreach;

import java.util.ArrayList;
import java.util.List;

public class ListForeach {
    public static void main(String[] args) {
        /*1 普通方式循环List*/
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

        for (String item : items) {
            System.out.println(item);
        }

        System.out.println("--------------------------------------------------------");

        /*2 在Java8中使用foreach+lambda表达式遍历List*/
        List<String> items2 = new ArrayList<>();
        items2.add("A");
        items2.add("B");
        items2.add("C");
        items2.add("D");
        items2.add("E");

        /*2.1 直接遍历*/
        items2.forEach(item -> System.out.println(item));
        /*2.2 方法引用 遍历*/
        items2.forEach(System.out::println);
        /*2.3 符合条件 遍历*/
        items2.forEach(item -> {
            if ("C".equals(item)) {
                System.out.println(item);
            }
        });
        /*2.4 filter过滤后 遍历*/
        items2.stream()
                .filter(s -> s.contains("B"))
                .forEach(System.out::println);
    }
}
