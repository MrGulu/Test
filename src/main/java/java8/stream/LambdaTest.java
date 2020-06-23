package java8.stream;

import domain.Emp;
import domain.Emp3;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.DateUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * @author tangwenlong
 */
@Slf4j
public class LambdaTest {
    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.java8Test();
        Emp emp = new Emp();
        log.info(emp.toString());
    }

    public void java8Test() {
        List<String> words = Arrays.asList("java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);
    }


    @org.junit.Test
    public void test0() {
        String s = "hello";
        String out = testObjectsRequireNonNull(s);
        System.out.println(out);
    }

    private static String testObjectsRequireNonNull(String t) {
        return Objects.requireNonNull(t, () -> "error");
    }
    /*******************************************************************************************************/
    /**
     * Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或Lambda操作符
     * 箭头操作符将Lambda表达式拆分成两部分：
     * () -> {}
     * 左侧：Lambda 表达式的参数列表
     * 右侧：Lambda 表达式中所需执行的功能，即Lambda体
     *
     * 说白了就是用Lambda实现接口，作为其实现类 放到 形参列表中
     * 但是要注意Lambda实现的接口必须是函数式接口（@FunctionalInterface注解修饰），只能有一个抽象方法（java8中的默认方法除外）
     */
    /**
     * 左右遇一括号省（左侧只有一个参数，可以省略();右侧方法体只有一条语句，{}和return都可以省略）
     * 左侧推断类型省（Lambda表达式的参数列表的数据类型可以省略不写）
     * 能省则省
     */

    /******************************************************************************************************************/
    /**
     * 消费型接口 Consumer<T>   有参无返               accept(T t);
     * 供给型接口 Supplier<T>   有返无参               T get();
     * 函数型接口 Function<T,R> 有参有返               R apply(T t);
     * 断言型接口 Predicate<T>  有参有返，返boolean     test(T t);
     */

    /******************************************************************************************************************/
    /**
     * 测试Consumer<T> 消费型接口
     */
    @org.junit.Test
    public void test1() {
        Map<String, Object> map = new HashMap<>(8);
        map.put("a", 1);
        map.put("b", 2.0);
        map.put("c", 3L);
        map.put("d", "ddd");
        map.put("e", true);
        map.put("f", 'f');
        test1Static(map, (x) -> {
            Iterator iterator = x.entrySet().iterator();
            System.out.println("==========while==========");
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = entry.getKey().toString();
                String value = entry.getValue().toString();
                log.info("{}={}", key, value);
            }
            System.out.println("==========for-each==========");
            for (Object o : x.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                String key = entry.getKey().toString();
                String value = entry.getValue().toString();
                log.info("{}={}", key, value);
            }
        });
    }

    private static void test1Static(Map<String, Object> map, Consumer<Map> consumer) {
        consumer.accept(map);
    }
    /******************************************************************************************************************/
    /**
     * 测试Supplier<T> 供给型接口
     */
    @Test
    public void test2() {
        List<String> list = test2Static(10, () -> {
            Random random = new Random();
            int randNum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
            String date = DateUtils.getDate2NumberString8(DateUtils.getCurrentDate());
            return date + Calendar.getInstance().getTimeInMillis() + randNum;
        });
        System.out.println(list);
    }

    private static List<String> test2Static(int num, Supplier<String> supplier) {
        List<String> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }
    /******************************************************************************************************************/
    /**
     * 暂时有问题
     * 20190715记录：
     *  已解决：
     *      发生原因：调用Arrays.asList();生成的List的add、remove方法时报异常，这是因为
     *          Arrays.asList()返回的是Arrays的内部类ArrayList， 而不是java.util.ArrayList。
     *          Arrays的内部类ArrayList和java.util.ArrayList都是继承AbstractList，
     *          remove、add等方法AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。
     *          java.util.ArrayList重写了这些方法而Arrays的内部类ArrayList没有重写，所以会抛出异常。
     *      解决方法：使用new ArrayList<>(Collection<? extends E> c)，将其转化为ArrayList。
     */
    /**
     * 测试Function<T,R> 函数型接口
     */
    @Test
    @SuppressWarnings("all")
    public void test3() {
        List<Emp3> paramList = Arrays.asList(new Emp3("张三", 18, 5555.55),
                new Emp3("李四", 19, 6666.66),
                new Emp3("王五", 20, 7777.77),
                new Emp3("赵六", 21, 8888.88));
        //之前讲到的倒序循环删除list中的元素
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
    /******************************************************************************************************************/
    /**
     * 测试Predicate<T> 断言型接口
     */
    @Test
    public void test4() {
        List<Emp3> paramList = Arrays.asList(new Emp3("张三", 18, 5555.55),
                new Emp3("李四", 19, 6666.66),
                new Emp3("王五", 20, 7777.77),
                new Emp3("赵六", 21, 8888.88));
        ListIterator<Emp3> listIterator = paramList.listIterator();
        while (listIterator.hasNext()) {
            Emp3 tmp = listIterator.next();
            if (test4Static(tmp, x -> x.getSalary() >= 7777.77)) {
                System.out.println(tmp);
            }
        }
    }

    private static boolean test4Static(Emp3 emp3, Predicate<Emp3> predicate) {
        return predicate.test(emp3);
    }
    /******************************************************************************************************************/
}
