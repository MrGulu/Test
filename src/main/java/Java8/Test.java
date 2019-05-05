package Java8;

import domain.Emp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;


public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.java8Test();
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
        Emp[] emps = new Emp[]{
                new Emp()
        };
    }

    @org.junit.Test
    public void test1() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("Hello World!");
    }
}
