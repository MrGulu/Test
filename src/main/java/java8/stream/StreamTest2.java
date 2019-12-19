package java8.stream;

import domain.Emp3;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 2.映射
 * map-接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，
 * 该函数会被应用到每个元素上，并将其映射成一个新的元素。
 * flatMap-接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
 */
public class StreamTest2 {

    private List<Emp3> emps = Arrays.asList(
            new Emp3("张三", 18, 5555.55),
            new Emp3("李四", 19, 6666.66),
            new Emp3("王五", 20, 7777.77),
            new Emp3("赵六", 21, 8888.88),
            new Emp3("赵六", 21, 8888.88),
            new Emp3("赵六", 21, 8888.88)
    );

    /**
     * <R> Stream<R> map(Function<? super T, ? extends R> mapper);
     */
    @Test
    public void test1() {
        System.out.println("1--------------------------");
        emps.stream()
                .filter(emp3 -> emp3.getSalary() <= 7777.77)
                .mapToDouble(Emp3::getSalary)
                .forEach(System.out::println);
        System.out.println("2--------------------------");
        emps.stream()
                .filter(emps -> emps.getSalary() <= 7777.77)
                .map(emp3 -> {
                    double salary = emp3.getSalary() + 1000.0;
                    emp3.setSalary(salary);
                    return emp3;
                }).forEach(System.out::println);
        /**
         * 我们发现Function 比 Consumer 多了一个 return。
         * 这也就是peek 与 map的区别了。
         * 总结：peek接收一个没有返回值的λ表达式，可以做一些输出，外部处理等。
         * map接收一个有返回值的λ表达式，之后Stream的泛型类型将转换为map参数
         * λ表达式返回的类型，并且还可以进行下一步的操作。而forEach是作为终止条件的，
         * 不能再进行下一步操作的。
         */
        /**
         * 还有一个疑问就是2已经形成一个新的流，现在3再通过emps.stream获取的流依然是2中处理过的流，
         *是缓存吗？还是什么原因呢
         */
        System.out.println("2可以简化--------------------------");
        emps.stream()
                .filter(emps -> emps.getSalary() <= 7777.77)
                .peek(emp3 -> {
                    double salary = emp3.getSalary() + 1000.0;
                    emp3.setSalary(salary);
                }).forEach(System.out::println);
        System.out.println("原来数据源数据并没有改变-------------------");
        System.out.println(emps);
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("a", "b", "cc", "eeee", "aaa", "bbb", "ccc", "ddd");
        list.stream()
                .filter(x -> x.length() >= 3)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    /**
     * 下面map调用的时候，strOperate方法返回的是一个Stream流，
     * 而Stream API中间操作本身返回的就是一个Stream，所以在执行完
     * map操作之后返回值是Stream<Stream<Character>>
     * <p>
     * 关联记忆：
     * 这种类似于Collections.add(E e)方法,直接把E类型添加进去。
     * 因为map方法本来就是将函数型接口的实现
     * 应用于每一个元素，而下面的操作每个元素，比如aaa，应用完之后，它的返回值是一个Stream。
     * 全部元素应用完后是多个流，这多个流放在一个中间操作形成的新流中。
     * 结构类似：
     * {{e,e,e,e},{a,a,a},{b,b,b},{c,c,c},{d,d,d}}
     */
    @Test
    public void test3() {
        List<String> list = Arrays.asList("a", "b", "cc", "eeee", "aaa", "bbb", "ccc", "ddd");
        Stream<Stream<Character>> stream = list.stream()
                .filter(x -> x.length() >= 3)
                .map(StreamTest2::strOperate);
        stream.forEach(x -> x.forEach(System.out::println));
    }

    /**
     * flatMap，虽然strOperate方法返回的仍然是一个Stream流，
     * 但是flatMap操作会将每个元素应用函数后所生成的一个个Stream流中的
     * 元素提取出来，然后放入flatMap中间操作后形成的新流！
     * <p>
     * 关联记忆：
     * 这种类似于Collections.addAll(collection<? extends E> coll)方法,
     * 是把传入的Collection类型中所包含的数据一个个拿出来，然后放入指定的集合中。
     * 因为map方法本来就是将函数型接口的实现
     * 结构类似：
     * {e,e,e,e,a,a,a,b,b,b,c,c,c,d,d,d}
     */
    @Test
    public void test4() {
        List<String> list = Arrays.asList("a", "b", "cc", "eeee", "aaa", "bbb", "ccc", "ddd");
        Stream<Character> stream = list.stream()
                .filter(x -> x.length() >= 3)
                .flatMap(StreamTest2::strOperate);
        stream.forEach(System.out::println);
    }

    private static Stream<Character> strOperate(String str) {
        List<Character> list = new ArrayList<>();
        for (Character c :
                str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }
}
