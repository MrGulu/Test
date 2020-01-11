package java8.optional;

import base.exception.BusinessException;
import domain.Emp3;
import io.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class OptionalTest {
    /**
     * 对于final修饰的变量，除非在构造函数赋初始值，否则编译不会通过。
     */
    private final int a;

    private final boolean b;

    private OptionalTest() {
        this.a = 1;
        this.b = true;
    }

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
        Optional<Emp3> firstEmp3 = emps.stream()
                .filter(emp3 -> emp3.getStatus().equals(Emp3.Status.FREE))
                .sorted(Comparator.comparingDouble(Emp3::getSalary))
                .findFirst();
        if (firstEmp3.isPresent()) {
            System.out.println(firstEmp3.get());
        }
        System.out.println("--------------------------------------");
        //或者下面的方式。
        firstEmp3.ifPresent(System.out::println);
    }

    @Test
    public void test2() {
        /**
         * 构造一个空的Optional对象，然后直接使用get的话，会抛出异常
         */
        Optional<Object> o = Optional.empty();
        //java.util.NoSuchElementException: No value present
        System.out.println(o.get());
    }

    @Test
    public void test3() {
        Object orElse = orElse(null);
        Object orElseGet = orElseGet(null);
        Object orElseThrow = null;
        try {
            orElseThrow = orElseThrow(null);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        log.info("\n***********************************************************");
        log.info("orElse:[{}],orElseGet:[{}],orElseThrow:[{}]", orElse, orElseGet, orElseThrow);
    }

    public static Object orElse(String s) {
        Optional<String> optional = Optional.ofNullable(s);
        return optional.orElse("");
    }

    public static Object orElseGet(String s) {
        Optional<String> optional = Optional.ofNullable(s);
        return optional.orElseGet(() -> {
            String s1 = "hello";
            return s1 + "world";
        });
    }

    public static Object orElseThrow(String s) {
        Optional<String> optional = Optional.ofNullable(s);
        return optional.orElseThrow(() -> new BusinessException("param s is null"));
    }

    @Test
    public void test4() {
        Student domain = new Student();
        domain.setAge(18);
        nullTest(domain);
    }

    public static void nullTest(Student domain) {
        String name = "";
        if (domain != null) {
            if (domain.getName() != null) {
                name = domain.getName();
            }
        }
        System.out.println(name);

        /**
         * 使用map时，如果opt不为null（value！=null），若map映射之后，输出为null，则会构建
         * 一个empty的opt，这样再调用orElse就没问题.
         * 下面效果同上面，但是会更加简洁，一行代码就代替了两个if嵌套的判断！
         */
        name = Optional.ofNullable(domain).map(Student::getName).orElse("");
        System.out.println(name);
    }


    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map);
    }
}
