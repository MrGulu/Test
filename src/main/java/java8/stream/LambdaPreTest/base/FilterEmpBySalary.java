package java8.stream.LambdaPreTest.base;

import domain.Emp3;

/**
 * @author tangwenlong
 * @description 根据工资过滤返回boolean
 */
public class FilterEmpBySalary implements MyPredicate<Emp3> {

    @Override
    public boolean test(Emp3 emp3) {
        return emp3.getSalary() > 7000;
    }
}
