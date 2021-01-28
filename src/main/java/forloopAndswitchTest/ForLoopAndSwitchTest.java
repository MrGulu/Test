package forloopAndswitchTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForLoopAndSwitchTest {

    /**
     * 　　首先看下break和continue的使用方法。
     *
     * break语句在循环和switch语句中使用，用于终止最近的封闭代码块，如果在嵌套循环中，则只终止最近的循环。
     *
     * continue语句在循环中使用，不能单独在switch中使用，可以在循环内的switch中使用，用于跳过当次循环，直接进入下一次循环。
     */

    @Test
    public void test1() {
        List<String> fruit = new ArrayList<>(Arrays.asList("apple", "banana", "orange", "pear"));
        for (int i = 0; i < fruit.size(); i++) {
            switch (fruit.get(i)) {
                case "orange":
//                continue;
                    break;
                default:
                    System.out.println("default");
            }
            System.out.println("we have " + fruit.get(i));
        }
    }

    @Test
    public void test2() {
        List<String> fruit = new ArrayList<>(Arrays.asList("apple", "banana", "orange", "pear"));
        for (int i = 0; i < fruit.size(); i++) {
            switch (fruit.get(i)) {
                case "orange":
                    continue;
//                break;
                default:
                    System.out.println("default");
            }
            System.out.println("we have " + fruit.get(i));
        }
    }
}
