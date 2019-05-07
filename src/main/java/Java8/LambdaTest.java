package Java8;

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
        emp.toString();
    }

    public void java8Test() {
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);
    }

    static {
        Emp3[] emps = new Emp3[]{
                new Emp3("张三", 18, 5555.55),
                new Emp3("李四", 19, 6666.66),
                new Emp3("王五", 20, 7777.77),
                new Emp3("赵六", 21, 8888.88)
        };
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

    /**
     * 左右遇一括号省
     * 左侧推断类型省
     * 能省则省
     */
    /**
     * 消费型接口 Consumer<T>   有参无返               accept(T t);
     * 供给型接口 Supplier<T>   有返无参               T get();
     * 函数型接口 Function<T,R> 有参有返               R apply(T t);
     * 断言型接口 Predicate<T>  有参有返，返boolean     test(T t)
     */

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

    /**
     * 测试Supplier<T> 消费型接口
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

    /**
     * 暂时有问题
     */
    @Test
    public void test3() {
        List<Emp3> paramList = Arrays.asList(new Emp3("张三", 18, 5555.55),
                new Emp3("李四", 19, 6666.66),
                new Emp3("王五", 20, 7777.77),
                new Emp3("赵六", 21, 8888.88));
        List<Emp3> list = test3Static(paramList, x -> {
            for (int i = x.size() - 1; i > 0; i--) {
                if (x.get(i).getSalary() <= 7777.77) {
                    x.remove(i);
                }
            }
            return x;
        });
    }

    private static List<Emp3> test3Static(List<Emp3> list, Function<List<Emp3>, List<Emp3>> function) {
        return function.apply(list);
    }

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
}
