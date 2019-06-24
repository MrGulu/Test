package java8;

import domain.Emp3;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 5.归约
 * reduce(T identity,BinaryOperator)-可以将流中元素反复结合起来，得到一个值。返回T
 * reduce(BinaryOperator)-可以将流中元素反复结合起来，得到一个值。返回Optional<T>
 */
public class StreamTest5 {

    private List<Emp3> emps = Arrays.asList(
            new Emp3("张三", 18, 5555.55, Emp3.Status.BUSY),
            new Emp3("唐三", 18, 5555.55, Emp3.Status.FREE),
            new Emp3("bbb", 18, 5555.55, Emp3.Status.VOCATION),
            new Emp3("aaa", 18, 5555.55, Emp3.Status.VOCATION),
            new Emp3("李四", 19, 6666.66, Emp3.Status.BUSY),
            new Emp3("王五", 20, 7777.77, Emp3.Status.FREE),
            new Emp3("赵六", 21, 8888.88, Emp3.Status.FREE)
    );

    /**
     * 1.map和reduce的连接通常称为map-reduce模式，因Google用它来进行网络搜索而出名(大数据)。
     */
    @Test
    public void test1() {
        /**
         * 第一种方法是带有identity参数的，相当于初始值，它的结果不可能是null，
         * 所以结果返回就是Integer类型的（T）
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("------------------------------");
        /**
         * 第二种方法是不带有identity参数的，没有初始值的话，它的结果就有可能是null，
         * 所以它的返回结果就是一个使用Optional容器包装的对象。
         */
        Optional<Double> salarySum = emps.stream()
                .map(Emp3::getSalary)
                .reduce(Double::sum);
        salarySum.ifPresent(System.out::println);
        //如果存在，返回；如果不存在，返回orElse参数值
        Double orElse = salarySum.orElse(9999.99);
        System.out.println(orElse);
        //如果存在，返回；如果不存在，抛出异常
        Double orElseThrow = salarySum.orElseThrow(NullPointerException::new);
        System.out.println(orElseThrow);
    }
}
