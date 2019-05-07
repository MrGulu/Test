package Java8;

import domain.Emp3;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 3.排序
 * sorted()-自然排序
 * sorted(Comparator com)-定制排序
 */
public class StreamTest3 {

    private List<Emp3> emps = Arrays.asList(
            new Emp3("张三", 18, 5555.55),
            new Emp3("唐三", 18, 5555.55),
            new Emp3("bbb", 18, 5555.55),
            new Emp3("aaa", 18, 5555.55),
            new Emp3("李四", 19, 6666.66),
            new Emp3("王五", 20, 7777.77),
            new Emp3("赵六", 21, 8888.88),
            new Emp3("赵六", 21, 8888.88),
            new Emp3("赵六", 21, 8888.88)
    );

    /**
     * 自然排序
     * 比如下面是String，它是实现了Comparable<String>接口，所以按照的就是
     * 重写的public int compare(String s1, String s2) 方法。
     * 还有比如Integer也是实现了这个接口，重写了compare方法。
     */
    @Test
    public void test1() {
        List<String> list = Arrays.asList("a", "b", "cc", "eeee", "aaa", "bbb", "ccc", "ddd");
        list.stream()
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * 定制排序
     * 参数是一个Comparator接口的实现，根据自己需要定义排序规则。
     */
    @Test
    public void test2() {
        emps.stream()
                .sorted((x, y) -> {
                    if (Objects.equals(x.getAge(), y.getAge())) {
//                    if (x.getAge().intValue() != y.getAge().intValue()) {
                        return x.getName().compareTo(y.getName());
                    } else {
                        return x.getAge().compareTo(y.getAge());
                    }
                }).forEach(System.out::println);
    }
}
