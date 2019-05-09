package Java8;

import com.alibaba.fastjson.JSON;
import domain.Emp3;
import org.junit.Test;
import utils.JacksonUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 6.收集
 * collect-将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
 */
public class StreamTest6 {

    private List<Emp3> emps = Arrays.asList(
            new Emp3("张三", 40, 5555.55, Emp3.Status.BUSY),
            new Emp3("唐三", 18, 5555.55, Emp3.Status.FREE),
            new Emp3("bbb", 45, 5555.55, Emp3.Status.VOCATION),
            new Emp3("aaa", 18, 5555.55, Emp3.Status.VOCATION),
            new Emp3("李四", 68, 6666.66, Emp3.Status.BUSY),
            new Emp3("王五", 20, 7777.77, Emp3.Status.FREE),
            new Emp3("赵六", 79, 8888.88, Emp3.Status.FREE)
    );

    @Test
    public void test1() {
        List<String> stringList = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.toList());
        stringList.forEach(System.out::println);

        System.out.println("-------------------------");

        Set<String> stringSet = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.toSet());
        stringSet.forEach(System.out::println);

        System.out.println("-------------------------");

        TreeSet<String> stringTreeSet = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.toCollection(TreeSet::new));
        stringTreeSet.forEach(System.out::println);

        System.out.println("-------------------------");
    }

    /**
     * 这个测试的是总数、平均值、最大值、最小值等
     * 但是这些方法参数都是函数型接口（……Function）
     * 所以目的是从流中的元素实体类中获取某一项属性后对提取出的属性进行计算总数、平均值、最大值、最小值等
     * <p>
     * 而在StreamTest4中测试的总数、平均值、最大值、最小值等
     * 这些方法参数都是Comparator比较器
     * 所以目的是直接计算流中元素的总数、平均值、最大值、最小值等
     * 或者map操作后，将某一属性取出，再进行计算。
     * <p>
     * ：注意两者的区别
     */
    @Test
    public void test2() {
        //总数
        Long aLong = emps.stream()
                .collect(Collectors.counting());

        Long bLong = emps.stream().count();

        Long cLong = (long) emps.size();
        System.out.println(aLong);

        //平均值
        /**
         * 操作的时候Collectors.averagingDouble(ToDoubleFunction<? super T> mapper)方法中
         * 参数已经指定了函数型接口，所以就不用先map映射了。
         */
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Emp3::getSalary));
        System.out.println(avg);

        //总和
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Emp3::getSalary));

        Double sum1 = emps.stream().mapToDouble(Emp3::getSalary).sum();
        System.out.println(sum);

        //最大值的员工
        Optional<Emp3> max = emps.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Emp3::getSalary)));

        Optional<Emp3> max1 = emps.stream().max(Comparator.comparingDouble(Emp3::getSalary));
        max.ifPresent(System.out::println);

        //最小值的工资
        Optional<Double> min = emps.stream()
                .map(Emp3::getSalary)
                .collect(Collectors.minBy(Double::compareTo));

        Optional<Double> min1 = emps.stream()
                .map(Emp3::getSalary).min(Double::compareTo);
        min.ifPresent(System.out::println);
    }

    /**
     * 分组
     */
    @Test
    public void test3() {
        Map<Emp3.Status, List<Emp3>> collect = emps.stream()
                .collect(Collectors.groupingBy(Emp3::getStatus));
        System.out.println(collect);
        System.out.println(JacksonUtil.ObjectToJson(collect));
        System.out.println(JSON.toJSONString(collect));
    }

    /**
     * 多级分组
     */
    @Test
    public void test4() {
        Map<Emp3.Status, Map<String, List<Emp3>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Emp3::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(collect);
        System.out.println(JacksonUtil.ObjectToJson(collect));
        System.out.println(JSON.toJSONString(collect));
    }

    /**
     * 分区 true/false
     */
    @Test
    public void test5() {
        Map<Boolean, List<Emp3>> collect = emps.stream()
                .collect(Collectors.partitioningBy(emp3 -> emp3.getSalary() >= 6666.66));
        System.out.println(collect);
        System.out.println(JacksonUtil.ObjectToJson(collect));
        System.out.println(JSON.toJSONString(collect));
        //下面的方法是输出json形式的数据
        System.out.println(JSON.toJSONString(collect, true));
        /**
         *  上面两种转json的形式为什么第二种不能转换
         *  第一种：
         *  {"false":[{"name":"张三","age":40,"salary":5555.55,"status":"BUSY"},{"name":"唐三","age":18,"salary":5555.55,"status":"FREE"},{"name":"bbb","age":45,"salary":5555.55,"status":"VOCATION"},{"name":"aaa","age":18,"salary":5555.55,"status":"VOCATION"}],"true":[{"name":"李四","age":68,"salary":6666.66,"status":"BUSY"},{"name":"王五","age":20,"salary":7777.77,"status":"FREE"},{"name":"赵六","age":79,"salary":8888.88,"status":"FREE"}]}
         *  第二种：
         *  {false:[{"age":40,"name":"张三","salary":5555.55,"status":"BUSY"},{"age":18,"name":"唐三","salary":5555.55,"status":"FREE"},{"age":45,"name":"bbb","salary":5555.55,"status":"VOCATION"},{"age":18,"name":"aaa","salary":5555.55,"status":"VOCATION"}],true:[{"age":68,"name":"李四","salary":6666.66,"status":"BUSY"},{"age":20,"name":"王五","salary":7777.77,"status":"FREE"},{"age":79,"name":"赵六","salary":8888.88,"status":"FREE"}]}
         *
         *  这两种结果的区别就在于JSON.toJSONString(collect)方法转成json串之后，把true和false还是当做原类型处理，
         *  而第一种将true和false转换成了json格式中要求的字符串形式，这样才是正确的Json格式。多加注意！
         *
         */
    }

    /**
     * 总结获取各种结果summarizingDouble……
     */
    @Test
    public void test6() {
        DoubleSummaryStatistics collect = emps.stream()
                .collect(Collectors.summarizingDouble(Emp3::getSalary));
        System.out.println(collect.getCount());
        System.out.println(collect.getAverage());
        System.out.println(collect.getSum());
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
    }

    /**
     * 连接joinning
     */
    @Test
    public void test7() {
        //拼接
        String collect = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.joining());
        System.out.println(collect);
        //分隔符
        String collect1 = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect1);
        //分隔符以及前缀、后缀
        String collect2 = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.joining(",", "===", "==="));
        System.out.println(collect2);
    }
}
