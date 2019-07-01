package collection.list;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * list中使用remove方法移除元素
 */
@SuppressWarnings("all")
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

    /**
     *  当调用容器的iterator方法返回Iterator对象时，把容器中包含对象的个数赋值给了一个变量expectedModCount，
     * 在调用next方法时会比较变量expectedModCount与容器中实际对象的个数modCount的值是否相等，若二者不相等，
     * 则会抛出ConcurrentModificationException异常，因此在使用Iterator遍历容器的过程中，如果对容器进行增加
     * 或删除操作，就会改变容器中对象的数量，从而导致抛出异常。
     * 下面运行结果：
     first
     second

     java.util.ConcurrentModificationException
     at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
     at java.util.ArrayList$Itr.next(ArrayList.java:851)
     at collection.list.ListLoopRemove.test1(ListLoopRemove.java:70)

        这是因为只有在调用到比较second时，才会调用add方法添加元素，这时候modCount的值才与一开始的expectedModCount值
     不一样，在下一次执行iter.next获取第三个元素的时候抛出了异常！
     */
    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
            String str = iter.next();
            System.out.println(str);
            if (str.equals("second")) {
                list.add("five");
//                list.remove("second");
            }
        }
    }

    /**
     *  若想对list进行添加或者删除操作，就不能直接调用list.add或者list.remove方法！
     * 可以使用Iterator对象，执行相应的添加或者删除元素的操作，这样一来，默认的count也会随之改变，不会发生异常。
     * 但是需要注意一点：
     *  1.如果只是使用Iterator类型对象，那么只能使用remove方法，不能添加元素
     *  2.如果想添加元素，需要使用ListIterator对象，该对象也支持remove方法等一些针对于list的额外方法，非常方便。
     *
     *  如果只是想在满足某些条件的情况下，删除某些元素，也可以将需要删除的元素放到一个集合当中，在遍历结束之后，
     * 直接使用list.removeAll方法删除即可。但是建议还是使用Iterator进行list的遍历与增加、删除元素操作。
     */
    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        for (ListIterator<String> iter = list.listIterator(); iter.hasNext(); ) {
            String str = iter.next();
            if (str.equals("first")) {
//                //删除当前位置的元素
//                iter.remove();
//                //是在删除的位置添加元素
//                iter.add("five");

                //上面两步操作，相当于下面一步操作，set方法用于元素的修改。
                iter.set("five");
            }
        }
        System.out.println(list);
        for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
            String str = iter.next();
            if (str.equals("second")) {
                iter.remove();
            }
        }
        System.out.println(list);
        List<String> removeList = new ArrayList<>();
        removeList.add("third");
        removeList.add("fourth");
        list.removeAll(removeList);
        System.out.println(list);
    }
}
