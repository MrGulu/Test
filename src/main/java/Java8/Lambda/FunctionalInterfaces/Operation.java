package Java8.Lambda.FunctionalInterfaces;

@FunctionalInterface
public interface Operation<T, R> {
    R calc(T t);
}
