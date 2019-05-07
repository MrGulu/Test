package Collection.list;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * list中使用remove方法移除元素
 */
public class ListLoopRemove {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        /*1.倒过来遍历*/
        for (int i = list.size() - 1; i > 0; i--) {
            if ("e".equals(list.get(i))) {
                list.remove(i);
                list.add(i, "a");
            }
        }
        System.out.println(list);
        /*2.每移除一个元素以后再把i移回来*/
        for (int i = 0; i < list.size(); i++) {
            if ("d".equals(list.get(i))) {
                list.remove(i);
                list.add(i, "b");
                i = i - 1;
            }
        }
        System.out.println(list);
        /*3.1.使用iterator.remove()方法删除*/
//        Iterator<String> iterator = list.iterator();
        /** 使用ListIterator可以添加元素，向前遍历，获取索引等操*/
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if ("c".equals(iterator.next())) {
                iterator.remove();
                iterator.add("listIterator");
            }
        }
        System.out.println(list);

        /*3.2.使用list.remove()方法删除*/
        list.removeIf("b"::equals);
        System.out.println(list);
    }
}
