package lombok;

import domain.Emp;
import domain.Emp2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
@Slf4j
public class TestLombok {
    public static void main(String[] args) {
        //使用Builder模式构建对象
        Emp emp = Emp.builder().testRequired("required").build();
        emp.setName("dd");
        System.out.println(emp.toString());

        System.out.println("---------------------------------------");

        Emp2 emp2 = Emp2.staticConstructor("required");
        emp2.setName("emp2");
        emp2.setAge(22);
        emp2.setSalary(9999.99);
        System.out.println(emp2.toString());
    }
    @Test
    public void test1() {
        Emp emp = new Emp();
        emp.setTestRequired("required").setName("张三").setAge(18).setSalary(9999.99).setTestExclude1("ceshi").setTestExclude2("ceshi2");
        System.out.println(emp.toString());
        log.info(emp.toString());
    }
}
