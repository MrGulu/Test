package Java8;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.java8Test();
    }
    public void java8Test() {
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);
    }
}
