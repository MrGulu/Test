package java8.other;

import org.junit.Test;

public class OtherTest {
    /**
     * 测试BiConsumer<T,R>接口中的默认方法
     * 查看BiConsumer接口默认方法代码可知，andThen方法是传入一个BiConsumer接口的实现，
     * 其实就是void accept(T t, U u);接口的实现，返回一个BiConsumer接口的实例。
     * 在使用的时候跟平时一样使用，直接调用这个实现的accept(T t,U u)方法就可以了，
     * 调用的时候就是直接调用accept(T t, U u)方法，因为函数式接口中只能存在一个方法（默认方法和静态方法除外）;
     * 过程就是按照你实现的andThen方法中的BiConsumer接口方法，它会先调用你这个类实现的accept(T t,U u)方法，
     * 因为你实现BiConsumer<T,R>接口的时候必须实现accept(T t,U u)方法，也就是已经定义了这个方法的实现。
     * 而andThen方法的意思是在执行了这个类实现的accept方法后再按照参数中的after接口实现做一些操作！
     */
    @Test
    public void test1() {
        BiConsumerTest biConsumerTest = new BiConsumerTest();
        biConsumerTest.andThen((x, y) -> {
            System.out.println(x);
            System.out.println(y);
        }).accept("hello", "world");
    }

}
