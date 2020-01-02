package designPatterns.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        log.info("10 + 5 = {}", context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        log.info("10 - 5 = {}", context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        log.info("10 * 5 = {}", context.executeStrategy(10, 5));
    }
}
