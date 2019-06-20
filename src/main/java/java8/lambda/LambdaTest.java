package java8.lambda;

import java8.lambda.FunctionalInterfaces.Operation;
import org.junit.Test;

public class LambdaTest {
    @Test
    public void test1() {
        Operation<Integer, Integer> operation = (x) -> x * x;
        Integer out = operation.calc(100);
        System.out.println(out);
    }
}
