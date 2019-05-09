package Java8;

import domain.Emp3;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 4.查找与匹配
 * allMatch-检查是否匹配所有元素
 * anyMatch-检查是否至少匹配一个元素
 * noneMatch-检查是否没有匹配所有元素
 * findFirst-返回第一个元素
 * findAny-返回第一个元素
 * count-返回流中元素的总个数
 * max-返回流中最大值
 * min-返回流中最小值
 */
public class StreamTest4 {

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
     * boolean allMatch(Predicate<? super T> predicate)
     */
    @Test
    public void test1() {
        boolean b1 = emps.stream()
                .allMatch(emp3 -> emp3.getAge() >= 18);
        System.out.println(b1);
    }

    /**
     * boolean anyMatch(Predicate<? super T> predicate)
     */
    @Test
    public void test2() {
        boolean b2 = emps.stream()
                .anyMatch(emp3 -> emp3.getStatus().equals(Emp3.Status.FREE));
        System.out.println(b2);
    }

    /**
     * boolean noneMatch(Predicate<? super T> predicate)
     */
    @Test
    public void test3() {
        boolean b3 = emps.stream()
                .noneMatch(emp3 -> emp3.getSalary() >= 8000);
        System.out.println(b3);
    }

    /**
     * Optional<T> findFirst()
     * <p>
     * isPresent()
     * 方法返回布尔类型数据，如果存在数据返回true，否则返回false；
     * ifPresent(Consumer<? super T> consumer)
     * 如果存在元素，则执行consumer操作
     */
    @Test
    public void test4() {
        Optional<Emp3> firstEmp3 = emps.stream()
                .filter(emp3 -> emp3.getStatus().equals(Emp3.Status.FREE))
                /*下面三种方式自定义排序*/
//                .sorted((e1,e2) -> e1.getSalary().compareTo(e2.getSalary()))
//                .sorted((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()))
                .sorted(Comparator.comparingDouble(Emp3::getSalary))
                .findFirst();
        if (firstEmp3.isPresent()) {
            System.out.println(firstEmp3.get());
        }
        System.out.println("--------------------------------------");
        //或者下面的方式。
        firstEmp3.ifPresent(System.out::println);
    }

    /**
     * emps.parallelStream()是采用并行流的方式；
     * 如果emps.stream()方法是采用串行流的方式，这种是按照顺序往下查找的，
     * 而并行流的方式是多个线程同时查找，谁先找到就用谁的结果。
     */
    @Test
    public void test5() {
        Optional<Emp3> firstEmp3 = emps.parallelStream()
                .filter(emp3 -> emp3.getStatus().equals(Emp3.Status.FREE))
                .findAny();
        firstEmp3.ifPresent(System.out::println);
    }

    /**
     * long count()
     */
    @Test
    public void test6() {
        long count = emps.stream()
                .filter(emp3 -> emp3.getStatus().equals(Emp3.Status.FREE))
                .count();
        System.out.println(count);
    }

    /**
     * Optional<T> max(Comparator<? super T> comparator)
     */
    @Test
    public void test7() {
        Optional<Emp3> max1 = emps.stream()
                .max((x, y) -> Integer.compare(x.getAge(), y.getAge()));
        max1.ifPresent(System.out::println);

        System.out.println("------------------------------------");
        //或者下面的方式。
        Optional<Emp3> max2 = emps.stream()
                .max(Comparator.comparingInt(Emp3::getAge));
        max2.ifPresent(System.out::println);
    }

    /**
     * Optional<T> min(Comparator<? super T> comparator)
     */
    @Test
    public void test8() {
        Optional<Emp3> min1 = emps.stream()
//                下面三种方式都可以
//                .min((x, y) -> x.getSalary().compareTo(y.getSalary()));
//                .min((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
                .min(Comparator.comparingDouble(Emp3::getSalary));
        min1.ifPresent(System.out::println);

        /**
         *  注意与上面的区别，上面的是取工资最小的员工信息，而下面是先将工资那一列取出来，然后取工资中的最小值。
         *  在使用min()方法时也有区别：
         *  1.下面是直接对经过映射的工资进行操作，因为min()方法中参数
         * 为Comparator<? super T> comparator；所以用下面两种方式都可以，一种是用Lambda表达式实现这个接口.
         * 另一个是简写的方法引用。
         * （这种方法引用采用的方式：〇类名::静态方法名   要求：静态方法名的参数列表和返回值要与函数式接口中方法的参数列表和返回值一致。
         *  还有两种方法引用的方式：①对象名::实例方法名 要求：实例方法名的参数列表和返回值要与函数式接口中方法的参数列表和返回值一致。
         *                     ②类名::实例方法名   要求：第一个参数作为方法的发起者，第二个参数作为方法的参数传入，
         *                      例如在函数式接口BiPredicate中的方法boolean test(T var1, U var2)使用equals方法，
         *                      String::equals相当于(x,y) -> x.equals(y)）
         *  2.上面是需要对一个个实体类中的工资进行比较，比下面的操作更深入一层，所以前两种方式可以使用Lambda表达式实现这个接口,
         * 上面也可以使用最后一种方式，这是Comparator接口的一个静态方法（JDK1.8之后接口中可以存在静态方法）
         * public static<T> Comparator<T> comparingDouble(ToDoubleFunction<? super T> keyExtractor)
         * 它的返回值也是Comparator类型的，但是参数是一个ToDoubleFunction，它的方法为double applyAsDouble(T var1)
         * 所以是接收一个T类型参数，返回值为固定的double。comparingDouble()方法最后还是调用的Double.compara()方法。
         * 而下面过滤出工资的情况是不适用这种方法的！多加揣摩一下！
         *
         */
        Optional<Double> minSalary = emps.stream()
                .map(Emp3::getSalary)
//                .min((x,y) -> x.compareTo(y));
                .min(Double::compare);
        minSalary.ifPresent(System.out::println);
    }
}
