package java8.foreach;

import java.util.ArrayList;
import java.util.List;

public class ListForeach {
    public static void main(String[] args) {
//        2.1 普通方式循环List
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

        for(String item : items){
            System.out.println(item);
        }

//        2.2 在Java8中使用foreach+lambda表达式遍历List
        List<String> items2 = new ArrayList<>();
        items2.add("A");
        items2.add("B");
        items2.add("C");
        items2.add("D");
        items2.add("E");

//lambda
//Output : A,B,C,D,E
        items2.forEach(item->System.out.println(item));
        //使用方法引用！
        items2.forEach(System.out::println);

//Output : C
        items2.forEach(item->{
            if("C".equals(item)){
                System.out.println(item);
            }
        });

//method reference
//Output : A,B,C,D,E
        items2.forEach(System.out::println);

//Stream and filter
//Output : B
        items2.stream()
                .filter(s->s.contains("B"))
                .forEach(System.out::println);
    }
}
