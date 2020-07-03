package java8.stream;

import domain.Emp3;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1.筛选与切片
 * filter-接收Lambda，从流中排除某些元素
 * limit-截断流，使其元素不超过给定数量
 * skip(n)-跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
 * distinct-筛选，通过流所生成元素的hashCode()和equals()去除重复元素
 */
public class StreamTest1 {

    private List<Emp3> emps = Arrays.asList(
            new Emp3("张三", 18, 5555.55),
            new Emp3("李四", 19, 6666.66),
            new Emp3("王五", 20, 7777.77),
            new Emp3("赵六", 21, 8888.88),
            new Emp3("赵六", 21, 8888.88),
            new Emp3("赵六", 21, 8888.88)
    );

    /**
     * Stream<T> filter(Predicate<? super T> predicate);
     * filter方法接收一个断言型接口的实现，结果是将stream流中的元素
     * 按照指定的方式过滤，返回一个新流，新流中包含的是已过滤掉元素后的
     * 数据。
     * Returns a stream consisting of the elements of this stream that match the given predicate
     * 返回由此流中与给定谓词匹配的元素组成的流
     */
    @Test
    public void test1() {
        Stream<Emp3> stream = emps.stream()
                .filter(emp3 -> emp3.getSalary() >= 6666.66);
        stream.forEach(System.out::println);
    }

    /**
     * Stream<T> limit(long maxSize);
     * 只拿出流中的前maxSize条元素，与skip互补
     * ps:
     * limit(3)那么会结合前面的filter来从emps里获取元素过滤，当达到limit(3)的数量时，
     * 就不会继续取emps中剩下的元素了！很智能。
     * 通过调试时Trace Current Stream Chain追踪发现。
     */
    @Test
    public void test2() {
        emps.stream()
                .filter(emp3 -> emp3.getSalary() >= 6666.66)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("********************************");
        emps.stream()
                .filter(emp3 -> emp3.getSalary() >= 6666.66)
                .limit(3)
                .forEach(System.out::println);
        System.out.println("********************************");
        emps.stream()
                .filter(emp3 -> emp3.getSalary() >= 5555.55)
                .limit(4)
                .forEach(System.out::println);
    }

    /**
     * Stream<T> skip(long n);
     * 只拿出流中的除了前n条外的元素，与limit互补
     */
    @Test
    public void test3() {
        emps.stream()
                .filter(emp3 -> emp3.getAge() >= 19)
                .skip(2)
                .forEach(System.out::println);
    }

    /**
     * Stream<T> distinct();
     * 将流中的重复记录去掉，只保留一条；
     * 此操作需要让流中的元素类，重写equals和hashCode方法！
     */
    @Test
    public void test4() {
        emps.stream()
                .filter(emp3 -> emp3.getSalary() >= 6666.66)
                .distinct()
                .skip(1)
                .forEach(System.out::println);
    }
}
