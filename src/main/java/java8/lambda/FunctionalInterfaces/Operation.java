package java8.lambda.FunctionalInterfaces;

@FunctionalInterface
public interface Operation<T, R> {
    R calc(T t);
}
