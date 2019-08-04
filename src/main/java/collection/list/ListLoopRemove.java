package collection.list;


import domain.Emp3;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

/**
 * list中使用remove方法移除元素
 */
@SuppressWarnings("all")
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
     * java.util.ArrayList重写了这些方法而Arrays的内部类ArrayList没有重写，所以会抛出异常。
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
                /**
                 * 当时用了iterator进行遍历list时，删除与添加元素是就不能使用list了，
                 * 只能使用iterator的相关方法，否则会抛出java.util.ConcurrentModificationException
                 * 详情请看test4()
                 */
//                list.remove("c");
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


     　快速失败只是一种直译，它不是音译，但，近似与意译。
     　　就如同手机一样，并不是只有拿在手里才能用，放在兜里，它照样能用。
     　　java中快速失败是指某个线程在迭代vector的时候，不允许其他线程修改该vector的内容。
     　　这样迭代器迭代出来的结果就会不准确，如用iterator迭代collection的时候，iterator就是另外起的一个线程，
     它去迭代collection，如果此时用collection.remove(obj)这个方法修改了collection里面的内容的时候，
     就会出现ConcurrentModificationException异常,这时候该迭代器就快速失败。
     */
    @Test
    public void test4() {
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
     *  1.如果只是使用Iterator类型对象，那么只能使用remove方法，不能添加元素(因为普通的Iterator对象没有add方法，而ListIterator有add方法)
     *  2.如果想添加元素，需要使用ListIterator对象，该对象也支持remove方法等一些针对于list的额外方法，非常方便。
     *
     *  如果只是想在满足某些条件的情况下，删除某些元素，也可以将需要删除的元素放到一个集合当中，在遍历结束之后，
     * 直接使用list.removeAll方法删除即可。但是建议还是使用Iterator进行list的遍历与增加、删除元素操作。
     */
    @Test
    public void test5() {
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

/**
 * 总结：
 * 1.在使用Iterator进行遍历时，不能使用list对象进行remove或add操作，要是用iterator对象操作
 * 2.在使用List进行遍历时，可以使用list对象进行remove或add操作，但是为了保证操作正确，需要进行
 * 相应的操作，详见test2()。
 * 也可以在正常遍历的情况下，remove之后紧接着执行add操作，这样size大小是不变的，所以不会引发相应错误。
 * 3.使用Arrays.asList()方法时，返回的List对象是一个，Arrays类的内部类对象，不能调用remove或者add方法，
 * 因为此类中未对这些方法重写，所以会抛出UnsupportedOperationException异常。
 * 4.综上可知，如果想对list进行remove和add操作，使用Iterator比较好（ListIterator）
 */
