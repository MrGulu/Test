package java8.other;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiConsumer;

@Slf4j
public class BiConsumerTest implements BiConsumer<Object, Object> {

    @Override
    public void accept(Object o, Object o2) {
        if (o.equals(o2)) {
            log.info("\n{} == {}", o, o2);
        } else {
            log.info("\n{} != {}", o, o2);
        }
    }
}
