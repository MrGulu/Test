package java8.stream.StreamPreTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author tangwenlong
 * @description Stream流的三个操作步骤
 */
public class StreamPreTest {

    /**
     * 一、Stream流的三个操作步骤
     * <p>
     * 1. 创建Stream
     * 2. 中间操作
     * 3. 终止操作（终端操作）
     */
    @Test
    public void test1() {
        /*1. 通过Collection系列集合提供的stream() 或 parallelStream()*/
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        /*2. 通过Arrays中的静态方法stream()获取数组流*/
        String[] strs = new String[]{"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(strs);

        /*3. 通过Stream类中的静态方法of()*/
        Stream<String> stream3 = Stream.of(strs);

        /*4. 创建无限流*/
        /*4.1 迭代*/
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 1);
        /*4.1 生成*/
        Stream<Double> stream5 = Stream.generate(Math::random);
    }
}
