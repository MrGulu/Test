package Java8.Lambda;

import Java8.Lambda.FunctionalInterfaces.Operation;
import org.junit.Test;

public class LambdaTest {
    @Test
    public void test1() {
        Operation<Integer> operation = (x) -> x * x;
        Integer out = operation.calc(100);
        System.out.println(out);
    }
}
