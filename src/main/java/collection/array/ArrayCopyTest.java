package collection.array;

import org.junit.Test;
import reflect.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayCopyTest {
    /**
     * Collections.copy
     */
    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        List<String> targetList = new ArrayList<>();
        /*如果不执行下一步操作，则会触发异常，具体查看Collections.copy()方法源码*/
        Collections.addAll(targetList, new String[list.size()]);
        Collections.copy(targetList, list);
        System.out.println(targetList);
    }

    /**
     * target数组未指定数组长度时，触发异常。
     * java.lang.ArrayIndexOutOfBoundsException
     */
    @Test
    public void test2() {
        String[] array = {"a", "b", "c", "d", "e"};
        String[] target = {};
        System.arraycopy(array, 0, target, 0, array.length);
        for (String s :
                target) {
            System.out.println(s);
        }
    }

    /**
     * System中提供了一个native静态方法arraycopy(),可以使用这个方法来实现数组之间的复制。
     * 对于一维数组来说，这种复制属性值传递，修改副本不会影响原来的值。
     * 对于二维或者一维数组中存放的是对象时，复制结果是一维的引用变量传递给副本的一维数组，修改副本时，会影响原来的数组。
     * ---------------------
     * 作者：qq_32440951
     * 来源：CSDN
     * 原文：https://blog.csdn.net/qq_32440951/article/details/78357325
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    @Test
    public void test3() {
        String[] array = {"a", "b", "c", "d", "e"};
        String[] target = new String[array.length];
        System.arraycopy(array, 0, target, 0, array.length);
        for (String s :
                target) {
            System.out.println(s);
        }
        System.out.println("****************************");
        array[4] = "a";
        for (String s :
                array) {
            System.out.println(s);
        }
        System.out.println("****************************");
        for (String s :
                target) {
            System.out.println(s);
        }
    }

    /**
     * 对于二维或者一维数组中存放的是对象时，复制结果是一维的引用变量传递给副本的一维数组，修改副本时，会影响原来的数组。
     */
    @Test
    public void test4() {
        Student[] array = {new Student(10), new Student(20), new Student(30)};
        Student[] target = new Student[array.length];
        System.arraycopy(array, 0, target, 0, array.length);
        for (Student s :
                target) {
            System.out.println(s.toString());
        }
        System.out.println("****************************");
        target[0].setId(30);
        for (Student s :
                array) {
            System.out.println(s.toString());
        }
        System.out.println("****************************");
        for (Student s :
                target) {
            System.out.println(s.toString());
        }
    }

    public static void main(String[] args) {
        String[] s1 = {"中国", "山西", "太原", "TYUT", "zyy", "加拿大", "不知道哪个州", "不知道哪个市", "不知道哪个学校", "yxf"};
        String[] s2 = new String[10];
        System.arraycopy(s1, 0, s2, 0, 10);
        s2[6] = "假设蒙大拿州";
        s2[7] = "假设蒙特利尔市";
        s2[8] = "假设Montreal商学院";

        System.out.println("This is s1");
        for (int i = 0; i < s1.length; i++) {
            System.out.print(s1[i] + ",");
        }

        System.out.println("\nThis is s2");
        for (int i = 0; i < s2.length; i++) {
            System.out.print(s2[i] + ",");
        }

        String[][] s3 = {{"中国", "山西", "太原", "TYUT", "zyy"}, {"加拿大", "不知道哪个州", "不知道哪个市", "不知道哪个学校", "yxf"}};
        String[][] s4 = new String[s3.length][s3[0].length];
        System.arraycopy(s3, 0, s4, 0, s3.length);

        System.out.println("\nThis is original s3");
        for (int i = 0; i < s3.length; i++) {
            for (int j = 0; j < s3[0].length; j++) {
                System.out.print(s3[i][j] + ",");
            }
        }

        s4[1][1] = "假设蒙大拿州";
        s4[1][2] = "假设蒙特利尔市";
        s4[1][3] = "假设Montreal商学院";

        System.out.println("\nThis is s3 after s4 has changed.");
        for (int i = 0; i < s3.length; i++) {
            for (int j = 0; j < s3[0].length; j++) {
                System.out.print(s3[i][j] + ",");
            }
        }

        System.out.println("\nThis is s4");
        for (int i = 0; i < s4.length; i++) {
            for (int j = 0; j < s4[0].length; j++) {
                System.out.print(s4[i][j] + ",");
            }
        }
    }
}
