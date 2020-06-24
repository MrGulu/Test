package java8.optional;

import domain.Emp3;
import org.junit.Test;

import java.util.Optional;

/**
 * @author tangwenlong
 * @description Optional与Lambda结合简化判空逻辑
 * @date 2020-06-20
 */
public class OptNullTest {
    /**
     * @description 存在则开干
     */
    @Test
    public void test1() {
        Emp3 emp3 = new Emp3("tang", 18, 9999.99);
        test1Method(emp3);
    }

    private static void test1Method(Emp3 emp3) {
        //java7
        if (emp3 != null) {
            System.out.println(emp3);
        }
        //java8
        Optional<Emp3> optional = Optional.ofNullable(emp3);
        optional.ifPresent(System.out::println);
    }

    /**
     * @description 存在则开干，无则返回屁
     */
    @Test
    public void test2() {
        Emp3 emp3 = new Emp3("tang", 18, 9999.99);
        System.out.println(test2MethodJava7(emp3));
        System.out.println(test2MethodJava8(emp3));
        System.out.println("********************************");
        System.out.println(test2MethodJava7(null));
        System.out.println(test2MethodJava8(null));
    }

    private static Object test2MethodJava7(Emp3 emp3) {
        //java7
        if (emp3 != null) {
            return emp3;
        } else {
            return "UNKNOWN_PERSON";
        }
    }

    private static Object test2MethodJava8(Emp3 emp3) {
        //java8
        Optional<Object> optional = Optional.ofNullable(emp3);
        return optional.orElse("UNKNOWN_PERSON");
    }

    /**
     * @description 存在则开干，无则由函数产生
     */
    @Test
    public void test3() {
        Emp3 emp3 = new Emp3("tang", 18, 9999.99);
        System.out.println(test3MethodJava7(emp3));
        System.out.println(test3MethodJava8(emp3));
        System.out.println("********************************");
        System.out.println(test3MethodJava7(null));
        System.out.println(test3MethodJava8(null));
    }

    private static Object test3MethodJava7(Emp3 emp3) {
        //java7
        if (emp3 != null) {
            return emp3;
        } else {
            return generateValue();
        }
    }

    private static Object test3MethodJava8(Emp3 emp3) {
        //java8
        Optional<Object> optional = Optional.ofNullable(emp3);
        return optional.orElseGet(OptNullTest::generateValue);
    }

    private static String generateValue() {
        return "UNKNOWN_PERSON";
    }

    /**
     * @description 夺命连环null检查
     */
    @Test
    public void test4() {
        Emp3 emp3 = new Emp3("tang", 18, 9999.99);
        System.out.println(test4MethodJava7(emp3));
        System.out.println(test4MethodJava8(emp3));
        System.out.println("********************************");
        System.out.println(test4MethodJava7(null));
        System.out.println(test4MethodJava8(null));
    }

    private static Object test4MethodJava7(Emp3 emp3) {
        //java7
        if (emp3 != null) {
            String name = emp3.getName();
            if (name != null) {
                return name.toUpperCase();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static Object test4MethodJava8(Emp3 emp3) {
        //java8
        Optional<Emp3> optional = Optional.ofNullable(emp3);
        return optional.map(Emp3::getName)
                .map(String::toUpperCase)
                .orElse(null);
    }
}
