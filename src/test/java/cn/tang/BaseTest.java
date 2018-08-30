package cn.tang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BaseTest {
    @Test
    public void test() {
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);
    }
    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list.toString());
        System.out.println("===============================");
        list.forEach(integer -> System.out.println(integer));
        System.out.println("===============================");
        list.forEach(System.out::println);
        System.out.println("===============================");
        List newList = list.stream().filter(integer -> integer >= 3).collect(toList());
        System.out.println(newList.toString());
    }
}
