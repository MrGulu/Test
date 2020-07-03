package java8.stream;

import com.alibaba.fastjson.JSON;
import domain.Emp3;
import org.junit.Test;
import utils.JacksonUtil;
import utils.StringUtils;

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
        /**
         * 转换成List
         */
        List<String> stringList = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.toList());
        stringList.forEach(System.out::println);
        System.out.println("-------------------------");

        /**
         * 转换成Set
         */
        Set<String> stringSet = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.toSet());
        stringSet.forEach(System.out::println);
        System.out.println("-------------------------");

        /**
         * 转换成TreeSet
         */
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
        System.out.println(bLong);
        System.out.println(cLong);

        System.out.println("**************************************************************");

        //平均值
        /**
         * 操作的时候Collectors.averagingDouble(ToDoubleFunction<? super T> mapper)方法中
         * 参数已经指定了函数型接口，所以就不用先map映射了。
         */
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Emp3::getSalary));
        System.out.println(avg);

        System.out.println("**************************************************************");

        //总和
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Emp3::getSalary));
        /**
         * 或者使用常规方式map后取sum.  要注意的是mapToDouble方法返回的是DoubleStream，sum方法为其独有的。
         * */
        Double sum1 = emps.stream().mapToDouble(Emp3::getSalary).sum();
        System.out.println(sum);
        System.out.println(sum1);

        System.out.println("**************************************************************");

        //最大值的员工
        /**
         * maxBy取出最大值的一条记录
         */
        Optional<Emp3> max = emps.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Emp3::getSalary)));

        Optional<Emp3> max1 = emps.stream().max(Comparator.comparingDouble(Emp3::getSalary));
        max.ifPresent(System.out::println);
        max1.ifPresent(System.out::println);

        System.out.println("**************************************************************");

        //最小值的工资
        /**
         * 先用map将stream流中的元素变成存储salary的流，然后在新流中调用minBy，也就相当于从整个流中取出了最小的一条记录。
         * 而上面取最大值一条记录的时候，因为没有调用map方法，所以流中存储的一个个元素仍然是实体类Emp3，所以maxBy取出的就是最大的一条记录。
         *
         * 总结：maxBy和minBy 以及 max和min 取出的都是按照传入的比较器比较之后的 流中存储的 最大或最小的 一条记录。
         *      其中使用.collect(Collectors.maxBy())方法可以与.max()相互替换。
         *
         *      取实体元素的最大最小，下面两个等价：
         *      .collect(Collectors.maxBy(Comparator.comparingDouble(Emp3::getSalary)))
         *      .max(Comparator.comparingDouble(Emp3::getSalary))
         *
         *      取实体元素中的某一列元素的最大最小，下面两个等价：
         *      .map(Emp3::getSalary).collect(Collectors.minBy(Double::compareTo))
         *      .map(Emp3::getSalary).min(Double::compareTo)
         */
        Optional<Double> min = emps.stream()
                .map(Emp3::getSalary)
                .collect(Collectors.minBy(Double::compareTo));

        Optional<Double> min1 = emps.stream()
                .map(Emp3::getSalary)
                .min(Double::compareTo);
        min.ifPresent(System.out::println);
        min1.ifPresent(System.out::println);
        System.out.println("**************************************************************");
    }

    /**
     * 分组
     */
    @Test
    public void test3() {
        Map<Emp3.Status, List<Emp3>> collect = emps.stream()
                .collect(Collectors.groupingBy(Emp3::getStatus));
        System.out.println(collect);
        System.out.println(JacksonUtil.objectToJson(collect));
        System.out.println(JSON.toJSONString(collect, true));
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
        System.out.println(JacksonUtil.objectToJson(collect));
        System.out.println(JSON.toJSONString(collect, true));
    }

    /**
     * 分区 true/false
     */
    @Test
    public void test5() {
        Map<Boolean, List<Emp3>> collect = emps.stream()
                .collect(Collectors.partitioningBy(emp3 -> emp3.getSalary() >= 6666.66));
        System.out.println(collect);
        System.out.println(JacksonUtil.objectToJson(collect));
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
     *  总结获取各种结果summarizingDouble……
     * 是取的某一列来进行分析。
     * 对于int、double、long作了特殊处理.
     * 获取对应类型的conut、sum、max、min、avg(总数、总和、最大最小平均值)
     */
    @Test
    public void test6() {
        DoubleSummaryStatistics collect = emps.stream()
                .collect(Collectors.summarizingDouble(Emp3::getSalary));
        System.out.println(collect.getCount());
        System.out.println(collect.getSum());
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
        System.out.println(collect.getAverage());
    }

    /**
     * 连接joinning
     */
    @Test
    public void test7() {
        //获取年龄为798的人的名字（主申人的名字）
        String name = emps.stream()
                .filter(emp3 -> emp3.getAge() == 798)
                .map(Emp3::getName)
                .collect(Collectors.joining());
        if (!StringUtils.isEmpty(name)) {
            System.out.println(name);
        } else {
            //假如没有匹配到，返回的是空串“”,因为Collectors.joining()方法内部是使用的StringBuilder
            //并不会返回null，在后面equals时导致NPE
            if (name.equals("test")) {
                System.out.println("ok");
            } else {
                System.out.println("NO PEOPLE!");
            }
        }

        System.out.println("***********************************");

        //拼接
        /**
         * 张三唐三bbbaaa李四王五赵六
         */
        String collect = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.joining());
        System.out.println(collect);

        System.out.println("***********************************");

        //分隔符
        /**
         * 张三,唐三,bbb,aaa,李四,王五,赵六
         */
        String collect1 = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect1);

        String[] split = collect1.split(",");
        System.out.println(Arrays.toString(split));

        System.out.println("***********************************");

        //分隔符以及前缀、后缀
        /**
         * ===张三,唐三,bbb,aaa,李四,王五,赵六===
         */
        String collect2 = emps.stream()
                .map(Emp3::getName)
                .collect(Collectors.joining(",", "===", "==="));
        System.out.println(collect2);
    }

    public static void main(String[] args) {
        String s = "";
        String[] split = s.split(",");
        System.out.println(Arrays.toString(split));
    }
}
