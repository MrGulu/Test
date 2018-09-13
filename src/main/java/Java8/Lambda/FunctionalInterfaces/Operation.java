package Java8.Lambda.FunctionalInterfaces;

@FunctionalInterface
public interface Operation<T> {
    Integer calc(T t);
}
