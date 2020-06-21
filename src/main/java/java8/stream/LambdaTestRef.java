package java8.stream;

import domain.Emp3;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author tangwenlong
 * @description 方法引用和构造器引用的测试
 */
public class LambdaTestRef {

    /**
     * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
     *         可以理解为方法引用是Lambda表达式的另外一种表现形式
     *
     * 主要有三种语法格式：
     * 1.对象::实例方法名
     * 2.类::静态方法名
     * 3.类::实例方法名
     *
     *
     * ps:
     * 1.Lambda体中调用方法的参数列表和返回值类型要与函数式接口中抽象方法的参数列表和返回值类型类型一致！
     * 例如：test1中Consumer接口中的抽象方法void accept(T t);与System.out的方法void println(String x)
     *      test2中Comparator接口中的抽象方法int compare(T o1, T o2);与Integer类的public static int compare(int x, int y)
     * 2.Lambda参数列表中第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用 类::实例方法名
     * */

    /**
     * 1.对象::实例方法名
     */
    @Test
    public void test1() {
        Consumer<String> consumer = x -> System.out.println(x);
        System.out.println("****************************************");
        Consumer<String> consumer2 = System.out::println;

        consumer.accept("abc");
        consumer2.accept("abc");

        System.out.println("****************************************");

        Emp3 emp3 = new Emp3("张三", 18, 9999.99);
        Supplier<String> supplier = () -> emp3.getName();
        System.out.println(supplier.get());

        Supplier<Integer> supplier1 = emp3::getAge;
        System.out.println(supplier1.get());
    }

    /**
     * 2.类::静态方法名
     */
    @Test
    public void test2() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(2, 3));

        Comparator<Integer> comparator1 = Integer::compare;
        System.out.println(comparator1.compare(3, 2));
    }

    /**
     * 3.类::实例方法名
     * ps:
     */
    @Test
    public void test3() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        System.out.println(biPredicate.test("x", "x"));

        BiPredicate<String, String> biPredicate1 = String::equals;
        biPredicate1.test("x", "x");
    }

    /**
     * 构造器引用
     * <p>
     * ClassName::new
     * <p>
     * ps：调用哪个构造器，取决于函数式接口中的抽象方法有几个参数以及参数的类型。
     */
    @Test
    public void test4() {
        Supplier<Emp3> supplier = () -> new Emp3();
        System.out.println(supplier.get());
        //自动匹配无参构造器（因为Supplier抽象方法T get()是无参的！！！）
        Supplier<Emp3> supplier1 = Emp3::new;
        System.out.println(supplier1.get());

        Function<Integer, Emp3> function = x -> new Emp3(x);
        //自动匹配无参构造器（因为Function抽象方法R apply(T t)是有一个参数的！！！）
        Function<Integer, Emp3> function1 = Emp3::new;
    }
}
