package java8.stream.LambdaPreTest;

import domain.Emp3;
import java8.stream.LambdaPreTest.base.FilterEmpByAge;
import java8.stream.LambdaPreTest.base.FilterEmpBySalary;
import java8.stream.LambdaPreTest.base.MyPredicate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tangwenlong
 * @description lambda代码过程展示
 */
public class LambdaPreTest {

    private List<Emp3> emps = Arrays.asList(
            new Emp3("张三", 18, 5555.55),
            new Emp3("李四", 19, 6666.66),
            new Emp3("王五", 20, 7777.77),
            new Emp3("赵六", 21, 8888.88),
            new Emp3("赵六", 21, 8888.88),
            new Emp3("赵六", 21, 8888.88)
    );

    /******************************************************************************************************************/
    /**
     * 需求： 获取年龄大于20的员工信息
     * 思路： 1.通过新建list，将传入的list中符合条件的元素添加进去
     * 2.直接使用传入的list进行操作，将不符合的元素剔除
     * 但是删除需要注意避免造成ConcurrentModificationException（不能使用增强for循环遍历删除）。
     * 三种方法：1.迭代器删除
     * 2.正向普通for循环遍历（删除时 i--）
     * 3.逆向普通for循环遍历
     */
    public List<Emp3> filterEmpByAge(List<Emp3> list) {
        List<Emp3> emp3list = new ArrayList<>();
        for (Emp3 emp :
                list) {
            if (emp.getAge() > 20) {
                emp3list.add(emp);
            }
        }
        return emp3list;
    }

    @Test
    public void test1() {
        List<Emp3> list = filterEmpByAge(emps);
        for (Emp3 emp :
                list) {
            System.out.println(emp);
        }
    }
    /******************************************************************************************************************/
    /**
     * 需求： 获取工资大于7000的员工信息
     * 缺点： 代码冗余，每增加一种过滤方式，都要加一个方法。
     */
    public List<Emp3> filterEmpBySalary(List<Emp3> list) {
        List<Emp3> emp3list = new ArrayList<>();
        for (Emp3 emp :
                list) {
            if (emp.getSalary() > 7000) {
                emp3list.add(emp);
            }
        }
        return emp3list;
    }

    @Test
    public void test2() {
        List<Emp3> list = filterEmpBySalary(emps);
        for (Emp3 emp :
                list) {
            System.out.println(emp);
        }
    }
    /******************************************************************************************************************/
    /**
     * 优化1： 使用策略模式
     * 缺点： 每增加一种过滤方式，都要添加一个实现类，很**
     */
    public List<Emp3> filterEmpByAge(List<Emp3> list, MyPredicate<Emp3> myPredicate) {
        List<Emp3> emp3List = new ArrayList<>();
        for (Emp3 emp :
                list) {
            if (myPredicate.test(emp)) {
                emp3List.add(emp);
            }
        }
        return emp3List;
    }

    @Test
    public void test3() {
        List<Emp3> list = filterEmpByAge(emps, new FilterEmpByAge());
        for (Emp3 emp :
                list) {
            System.out.println(emp);
        }
        System.out.println("*******************************************");
        List<Emp3> list2 = filterEmpByAge(emps, new FilterEmpBySalary());
        for (Emp3 emp :
                list2) {
            System.out.println(emp);
        }
    }
    /******************************************************************************************************************/
    /**
     * 优化2：匿名内部类
     * 缺点：无用代码太多，代码不够简洁
     */
    @Test
    public void test4() {
        List<Emp3> list = filterEmpByAge(emps, new MyPredicate<Emp3>() {
            @Override
            public boolean test(Emp3 emp3) {
                return emp3.getAge() > 20;
            }
        });
        for (Emp3 emp :
                list) {
            System.out.println(emp);
        }
        System.out.println("*******************************************");
        List<Emp3> list2 = filterEmpByAge(emps, new MyPredicate<Emp3>() {
            @Override
            public boolean test(Emp3 emp3) {
                return emp3.getSalary() > 7000;
            }
        });
        for (Emp3 emp :
                list2) {
            System.out.println(emp);
        }
    }
    /******************************************************************************************************************/
    /**
     * 优化3：lambda表达式
     * 缺点：有额外的接口增加
     */
    @Test
    public void test5() {
        List<Emp3> list = filterEmpByAge(emps, emp3 -> emp3.getAge() > 20);
        list.forEach(System.out::println);
        System.out.println("*******************************************");
        List<Emp3> list2 = filterEmpByAge(emps, emp3 -> emp3.getSalary() > 7000);
        list2.forEach(System.out::println);

    }
    /******************************************************************************************************************/
    /**
     * 优化4：stream流式语法
     * 优点：牛皮！强无敌！仅需要list集合即可
     */
    @Test
    public void test6() {
        emps.stream()
                .filter(emp3 -> emp3.getAge() > 20)
                .forEach(System.out::println);
        System.out.println("*******************************************");
        emps.stream()
                .filter(emp3 -> emp3.getSalary() > 7000)
                .forEach(System.out::println);
        System.out.println("*******************************************");
    }
    /******************************************************************************************************************/
}
