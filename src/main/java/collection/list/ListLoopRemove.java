package collection.list;


import domain.Emp3;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;

/**
 * list中使用remove方法移除元素
 */
public class ListLoopRemove {

    /**
     * List每remove掉一个元素以后，后面的元素都会向前移动，此时如果执行i=i+1，则刚刚移过来的元素没有被读取。
     * 解决方法：
     * test2方法中的几种方式
     */
    @Test
    public void test1() {
        String str1 = new String("abcde");
        String str2 = new String("abcde");
        String str3 = new String("abcde");
        String str4 = new String("abcde");
        String str5 = new String("abcde");
        List<String> list = new ArrayList<>();

        list.add(str1);
        list.add(str2);
        list.add(str3);
        list.add(str4);
        list.add(str5);

        System.out.println("list.size()=" + list.size());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).startsWith("abcde")) {
                list.remove(i);
            }
        }
        System.out.println("after remove:list.size()=" + list.size());
    }

    @Test
    public void test2() {
        String str1 = new String("abcde");
        String str2 = new String("abcde");
        String str3 = new String("abcde");
        String str4 = new String("abcde");
        String str5 = new String("abcde");
        List<String> list = new ArrayList<>();

        list.add(str1);
        list.add(str2);
        list.add(str3);
        list.add(str4);
        list.add(str5);
        /*1.倒过来遍历*/
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).startsWith("abcde")) {
                list.remove(i);
            }
        }
        System.out.println(list);
        /*2.每移除一个元素以后再把i移回来*/
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).startsWith("abcde")) {
                list.remove(i);
                i = i - 1;
            }
        }
        System.out.println(list);
        /*3.1.使用iterator.remove()方法删除*/
//        Iterator<String> iterator = list.iterator();
        /** 使用ListIterator可以添加元素，向前遍历，获取索引等操*/
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().startsWith("abcde")) {
                iterator.remove();
            }
        }
        System.out.println(list);

        /*3.2.使用list.remove()方法删除*/
        //底层还是使用iterator进行操作
        list.removeIf(o -> o.startsWith("abcde"));
        System.out.println(list);
    }

    /**
     * 暂时有问题
     * 20190715记录：
     * 已解决：
     * 发生原因：调用Arrays.asList()生产的List的add、remove方法时报异常，这是由Arrays.asList()
     * 返回的是Arrays的内部类ArrayList， 而不是java.util.ArrayList。
     * Arrays的内部类ArrayList和java.util.ArrayList都是继承AbstractList，
     * remove、add等方法AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。
     * java.util.ArrayList重新了这些方法而Arrays的内部类ArrayList没有重新，所以会抛出异常。
     * 解决方法：使用new ArrayList<>(Collection<? extends E> c)，将其转化为ArrayList。
     */
    @Test
    @SuppressWarnings("all")
    public void test3() {
        List<Emp3> paramList = Arrays.asList(new Emp3("张三", 18, 5555.55),
                new Emp3("李四", 19, 6666.66),
                new Emp3("王五", 20, 7777.77),
                new Emp3("赵六", 21, 8888.88));
        List<Emp3> list = test3Static(new ArrayList<>(paramList), x -> {
            for (int i = x.size() - 1; i > 0; i--) {
                if (x.get(i).getSalary() <= 7777.77) {
                    x.remove(i);
                }
            }
            return x;
        });
        System.out.println(list);
    }

    private static List<Emp3> test3Static(List<Emp3> list, Function<List<Emp3>, List<Emp3>> function) {
        return function.apply(list);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        /*1.倒过来遍历*/
        for (int i = list.size() - 1; i >= 0; i--) {
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
        //底层还是使用iterator进行操作
        list.removeIf("b"::equals);
        System.out.println(list);
    }

}
